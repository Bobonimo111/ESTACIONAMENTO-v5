package br.com.estacionamento.views;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import br.com.estacionamento.model.Cliente;
import br.com.estacionamento.model.Funcionario;
import br.com.estacionamento.model.Ticket;
import br.com.estacionamento.model.Vaga;
import br.com.estacionamento.model.Veiculo;
import br.com.estacionamento.service.ClienteService;
import br.com.estacionamento.service.TicketService;
import br.com.estacionamento.service.VeiculoService;

public class TicketView {
    public static void ticketView(Scanner scanner, TicketService ticketService, VeiculoService veiculoService,
            Funcionario funcionario, ClienteService clienteService) {
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
                    cadastrarTicket(scanner, ticketService, veiculoService, null, clienteService);
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
        Funcionario funcionario, ClienteService clienteService) {
        System.out.println("\n--- GERAR NOVO TICKET ---");
        System.out.println("Informe o CPF do cliente:");
        String cpf = scanner.nextLine();

        Cliente cliente = clienteService.buscarClientePorCpf(cpf);
        if (cliente == null) {
            System.out.println("Cliente não encontrado");
            return;
        }

        System.out.println("Qual tempo de permanência em horas [APENAS NÚMEROS]: ");
        int horas = scanner.nextInt();
        scanner.nextLine();
        if (horas <= 0) {
            System.out.println("Tempo inválido.");
            return;
        }

        LocalDateTime horaSaida = formatadorDeHoras(scanner); // Exemplo simples, pode ser validado

        System.out.println("Qual placa do veículo:");
        String placa = scanner.nextLine();

        Veiculo veiculo = veiculoService.buscarVeiculoPorPlaca(placa);
        if (veiculo == null) {
            System.out.println("Veículo não encontrado");
            return;
        }

        Ticket ticket = new Ticket();
        ticket.setCliente(cliente);
        ticket.setVeiculo(veiculo);
        ticket.setTempoPermanencia(horas);
        ticket.setHoraSaida(horaSaida);

        // BigDecimal valorHora = new BigDecimal("7.50");
        // BigDecimal valorTotal = valorHora.multiply(BigDecimal.valueOf(horas));
        Ticket ticketSaved = ticketService.abrirTicket(ticket);
        veiculo.adicionarTicket(ticketSaved);
        System.out.println(" ticket adicionado ao cliente "+ticketSaved.getCliente().getName());
        // System.out.println("Vaga que vai estacionar");

        // System.out.println("Andar: ");
        // String andar = scanner.nextLine();

        // System.out.println("Setor: ");
        // String setor = scanner.nextLine();

        // List<Vaga> vaga = veiculoService.buscarVagasPorSetorAndar(andar, setor);
        // veiculo.setVaga(vaga.get(0));

        // System.out.println("Valor aplicado é : " + valorTotal);

        // Pagamento pagamento = new Pagamento();
        // pagamento.setValor(valorTotal);
        // ticketService.gerarPagamento(pagamento,ticketSaved);
        // System.out.println("Pagamento gerado com sucesso");
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

            System.out.print("Tempo de permanencia:");
            ticket.setTempoPermanencia(scanner.nextInt());
            scanner.nextLine();

            ticket.setHoraSaida(
                    formatadorDeHoras(scanner));

            ticketService.atualizarTicket(ticket);
            System.out.println("Ticket atualizado");
        }

    }

    public static void listarTickets(Scanner scanner, TicketService ticketService) {
        ticketService.buscarTodosTickets().forEach(ticket -> {
            System.out.println("=============================================");
            System.out.println("Tempo de permanencia : "+ticket.getTempoPermanencia() + " horas");
            System.out.println("Hora de saida : "+ticket.getHoraSaida());
            System.out.println("Modelo do veiculo : " + ticket.getVeiculo().getModelo());
            System.out.println("Dono : " + ticket.getCliente().getName());
        });
    }

    public static void removerTicket(Scanner scanner, TicketService ticketService) {
        System.out.println("Buscar ticket por id:");
        int id = scanner.nextInt();
        Ticket ticket = ticketService.buscarTicketPorId((long) id);

        if (ticket == null) {
            System.out.println("Ticket não encontrado");
        } else {
            ticketService.fecharTicket(ticket.getId());
        }
    }

    private static LocalDateTime formatadorDeHoras(Scanner scanner) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        LocalTime horaDigitada = null;

        while (horaDigitada == null) {
            try {
                System.out.print("Digite a hora de saída (formato HH:mm, ex: 14:30): ");
                String entrada = scanner.nextLine();
                horaDigitada = LocalTime.parse(entrada, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Formato inválido. Tente algo como 14:30");
            }
        }

        // Combina com a data atual
        LocalDateTime horaSaida = LocalDateTime.of(LocalDate.now(), horaDigitada);

        return horaSaida;
    }
}
