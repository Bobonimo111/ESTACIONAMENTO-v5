package br.com.estacionamento.views;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import br.com.estacionamento.model.Funcionario;
import br.com.estacionamento.model.Pagamento;
import br.com.estacionamento.model.Ticket;
import br.com.estacionamento.model.Veiculo;
import br.com.estacionamento.service.TicketService;
import br.com.estacionamento.service.VeiculoService;

public class TicketView {
    public static void ticketView(Scanner scanner, TicketService ticketService, VeiculoService veiculoService,
            Funcionario funcionario) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== SISTEMA DE TICKET ===");

            System.out.println("1 - Cadastrar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Listar");
            System.out.println("4 - Remover");

            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarTicket(scanner, ticketService, veiculoService, null);
                    break;
                case 2:
                    atualizarTicket(scanner, ticketService, veiculoService, null);
                    break;
                case 3:
                    listarTickets(scanner, ticketService);
                    break;
                case 4:
                    removerTicket(scanner, ticketService);
                    break;
                case 0:
                    running = false;
                    System.out.println("Saindo do gerenciador de Tickets");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public static void cadastrarTicket(Scanner scanner, TicketService ticketService, VeiculoService veiculoService,
            Funcionario funcionario) {
        // Funcionario vai ser passado ao acessar a view
        System.out.println("\n--- GERAR NOVO TICKET ---");
        Ticket ticket = new Ticket();
        Pagamento pagamento = new Pagamento();

        System.out.println("Qual tempo de permanencia em horas[APENAS NUMEROS]: ");
        int horas = scanner.nextInt();
        scanner.nextLine();
        Double valor = 7.50;
        ticket.setTempoPermanencia(horas);

        System.out.println("Valor aplicado é : " + horas * valor);
        pagamento.setValor(new BigDecimal(horas * valor));

        System.out.println("Qual placa do veiculo: ");
        String placa = scanner.nextLine();
        Veiculo veiculo = veiculoService.buscarVeiculoPorPlaca(placa);
        if (veiculo == null) {
            System.out.println("Veiculo não encontrado");
        } else {
            ticket.setVeiculo(veiculo);
        }

        ticketService.abrirTicket(ticket);
        pagamento.setTicket(ticket);
        ticketService.gerarPagamento(pagamento);

        System.out.println("Pagamento gerado com sucesso");
    }

    public static void atualizarTicket(Scanner scanner, TicketService ticketService, VeiculoService veiculoService,
            Funcionario funcionario) {
        System.out.println("Buscar ticket por id:");
        int id = scanner.nextInt();
        Ticket ticket = ticketService.buscarTicketPorId((long) id);

        if (ticket == null) {
            System.out.println("Ticket não encontrado");
        } else {
            System.out.println(ticket);
            // id=%s,
            // tempoPermanencia=%s,
            // horaSaida=%s,
            // pagamento=%s,
            // cliente=%s,
            // veiculo=%s,
            // funcionario=%s

            System.out.print("Tempo de permanencia:");
            ticket.setTempoPermanencia(scanner.nextInt());
            scanner.nextLine();

            System.out.print("hora saida [Dia/Mês/Ano Hora:Minuto]: ");
            String entrada = scanner.nextLine();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime horaSaida = LocalDateTime.parse(entrada, formatter);

            ticket.setHoraSaida(horaSaida);

            ticketService.atualizarTicket(ticket);
            System.out.println("Ticket atualizado");
        }

    }

    public static void listarTickets(Scanner scanner, TicketService ticketService) {
        ticketService.buscarTodosTickets().forEach(ticket -> System.out.println(ticket));
    }

    public static void removerTicket(Scanner scanner,TicketService ticketService) {
        System.out.println("Buscar ticket por id:");
        int id = scanner.nextInt();
        Ticket ticket = ticketService.buscarTicketPorId((long) id);

        if(ticket == null){
            System.out.println("Ticket não encontrado");
        }else{
            ticketService.fecharTicket(ticket.getId());
        }
    }
}
