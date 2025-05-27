package br.com.estacionamento;

import br.com.estacionamento.model.*;
import br.com.estacionamento.service.*;
import br.com.estacionamento.views.ClienteView;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class EstacionamentoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EstacionamentoApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(ClienteService clienteService, VeiculoService veiculoService, FuncionarioService funcionarioService, TicketService ticketService) {
        return args -> {
            Scanner scanner = new Scanner(System.in);
            boolean running = true;

            while (running) {
                System.out.println("\n=== SISTEMA DE ESTACIONAMENTO ===");
                //Funcionario -> cliente
                //Funcionario -> Ticket
                //Funcionario -> Vagas
                //Funcionario -> Relatorios
                //Funcionario -> Veiculos

                System.out.println("1 - Cliente");
                System.out.println("2 - Ticket");
                System.out.println("3 - Vagas");
                System.out.println("4 - Relatorio");
                System.out.println("5 - Veiculos");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");

                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        ClienteView.clienteView(scanner,clienteService);
                        break;
                    case 2:
                        cadastrarVeiculoParaCliente(scanner, clienteService, veiculoService);
                        break;
                    case 3:
                        cadastarFuncionario(scanner, funcionarioService);
                        break;
                    case 4:
                        break;
                    case 5:

                        break;
                    case 0:
                        running = false;
                        System.out.println("Saindo do sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            }
            scanner.close();
            System.exit(0);
        };
    }

    private List<Ticket> listarTodosTickets(Scanner scanner, TicketService ticketService, FuncionarioService funcionarioService) {
        System.out.println("\n--- LISTA DE TODOS OS TICKETS ---");

        System.out.println("Digite CPF do funcionário");
        var cpfFuncionario = scanner.nextLine();
        var funcionario = funcionarioService.buscarFuncionarioPorCpf(cpfFuncionario);

        if (funcionario == null) {
            System.out.println("Você não tem permissão para acessar esta funcionalidade.");
            return List.of();
        }

        try {
            List<Ticket> tickets = ticketService.buscarTodosTickets();
            if (tickets.isEmpty()) {
                System.out.println("Nenhum ticket encontrado.");
            } else {
                tickets.forEach(ticket -> System.out.println("Ticket " + "Veículo: " + ticket.getVeiculo().getPlaca() + ", Valor: " + ticket.getValor()));
            }
            return tickets;
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
            return List.of();
        }
    }

    private List<Ticket> listarMeusTickets(Scanner scanner, TicketService ticketService) {
        System.out.println("\n--- LISTA DE TICKETS ---");
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();

        try {
            List<Ticket> tickets = ticketService.buscarTicketPorCpf(cpf);
            if (tickets.isEmpty()) {
                System.out.println("Nenhum ticket encontrado para o cliente com CPF " + cpf);
            } else {
                tickets.forEach(ticket -> System.out.println("Ticket " + "Veículo: " + ticket.getVeiculo().getPlaca() + ", Valor: " + ticket.getValor()));
            }
            return tickets;
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
            return List.of();
        }
    }

    private List<Veiculo> listarMeusVeiculos(Scanner scanner, ClienteService clienteService) {
        System.out.println("\n--- LISTA DE VEÍCULOS ---");
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();

        try {
            var cliente = clienteService.buscarClientePorCpf(cpf);
            List<Veiculo> veiculos = cliente.getVeiculos();
            if (veiculos.isEmpty()) {
                System.out.println("Nenhum veículo cadastrado para o cliente " + cliente.getNome());
            } else {
                veiculos.forEach(veiculo -> System.out.println("Placa: " + veiculo.getPlaca() + ", Modelo: " + veiculo.getModelo()));
            }
            return veiculos;
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
            return List.of();
        }
    }

    private static void cadastrarVeiculoParaCliente(Scanner scanner, ClienteService clienteService, VeiculoService veiculoService) {
        System.out.println("\n--- CADASTRO DE VEÍCULO ---");

        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();

        try {
            var cliente = clienteService.buscarClientePorCpf(cpf);

            var veiculo = new Veiculo();

            System.out.print("Modelo: ");
            veiculo.setModelo(scanner.nextLine());

            System.out.print("Marca: ");
            veiculo.setMarca(scanner.nextLine());

            System.out.print("Cor: ");
            veiculo.setCor(scanner.nextLine());

            veiculo.setCliente(cliente);
            veiculoService.cadastrarVeiculo(veiculo);

            System.out.println("Veículo cadastrado com sucesso para o cliente " + cliente.getNome());
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void  cadastarFuncionario(Scanner scanner, FuncionarioService funcionarioService) {
        System.out.println("\n--- CADASTRO DE FUNCIONÁRIO ---");
        var funcionario = new Funcionario();

        System.out.print("CPF: ");
        funcionario.setCpf(scanner.nextLine());

        System.out.print("Nome: ");
        funcionario.setNome(scanner.nextLine());

        System.out.println("Telefone: ");
        funcionario.setTelefone(scanner.nextLine());

        funcionarioService.cadastrarFuncionario(funcionario);
        System.out.println("Funcionário cadastrado com sucesso!");
    }

    private static void criarTicket(Scanner scanner, VeiculoService veiculoService, ClienteService clienteService, FuncionarioService funcionarioService, TicketService ticketService /*,EstacionamentoService estacionamentoService*/) {
        System.out.println("\n--- CRIAÇÃO DE TICKET ---");

        System.out.println("Digite o CPF do funcionário: ");
        String cpfFuncionario = scanner.nextLine();
        var funcionario = funcionarioService.buscarFuncionarioPorCpf(cpfFuncionario);

        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();
        var cliente = clienteService.buscarClientePorCpf(cpf);

        var veiculos = veiculoService.buscarVeiculoPorPlaca(cliente.getVeiculos().getFirst().getPlaca());

        System.out.println("Nome do Estacionamento: ");
//        var estacionamento = estacionamentoService.buscarEstacionamentoPorNome(scanner.nextLine());

        System.out.println("Valor: ");
        BigDecimal valor = new BigDecimal(scanner.nextLine());

        System.out.println("Quantas horas o cliente vai ficar? ");
        int quantidadeHoras = Integer.parseInt(scanner.nextLine());

        LocalDateTime horaEntrada = LocalDateTime.now();
        LocalDateTime horaSaida = horaEntrada.plusHours(quantidadeHoras);

        var ticket = Ticket.builder()
                .funcionario(funcionario)
                .cliente(cliente)
                .veiculo(veiculos)
//                .horaEntrada(horaEntrada)
//                .estacionamento(estacionamento)
                .valor(valor)
                .horaSaida(horaSaida)
                .build();

        ticketService.abrirTicket(ticket);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        System.out.println("Ticket criado com sucesso!");
        System.out.println("Hora de entrada: " + horaEntrada.format(formatter));
        System.out.println("Hora prevista de saída: " + horaSaida.format(formatter));
    }

}
