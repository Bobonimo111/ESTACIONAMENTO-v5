package br.com.estacionamento.views;

import br.com.estacionamento.model.Cliente;
import br.com.estacionamento.model.Endereco;
import br.com.estacionamento.service.ClienteService;
import java.util.List;
import java.util.Scanner;



public class ClienteView {

    public static void clienteView(Scanner scanner, ClienteService clienteService) {
        boolean running = true;

        while (running) {
            System.out.println("\n=== SISTEMA DE CLIENTES ===");

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
                    cadastrarCliente(scanner, clienteService);
                    break;
                case 2:
                    atualizarCliente(scanner, clienteService);
                    break;
                case 3:
                    listarClientes(scanner, clienteService);
                    break;
                case 4:
                    removerCliente(scanner, clienteService);
                    break;
                case 0:
                    running = false;
                    System.out.println("Saindo do gerenciador de clientes");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void cadastrarCliente(Scanner scanner, ClienteService clienteService) {
        System.out.println("\n--- CADASTRO DE CLIENTE ---");
        Cliente cliente = new Cliente();

        System.out.print("CPF: ");
        cliente.setCpf(scanner.nextLine());

        System.out.print("Nome: ");
        cliente.setName(scanner.nextLine());

        System.out.print("Telefone: ");
        cliente.setTelefone(scanner.nextLine());

        Endereco endereco = new Endereco();

        System.out.print("Rua: ");
        endereco.setRua(scanner.nextLine());

        System.out.print("Bairro: ");
        endereco.setBairro(scanner.nextLine());

        System.out.print("Complemento: ");
        endereco.setComplemento(scanner.nextLine());

        System.out.print("Cidade: ");
        endereco.setCidade(scanner.nextLine());

        System.out.print("CEP: ");
        endereco.setCep(scanner.nextLine());

        System.out.print("UF: ");
        endereco.setUf(scanner.nextLine());

        clienteService.cadastrarCliente(cliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private static void atualizarCliente(Scanner scanner, ClienteService clienteService) {
        System.out.println("Digite o cpf de quem deseja editar");
        String cpf = scanner.nextLine();

        Cliente cliente = clienteService.buscarClientePorCpf(cpf);

        System.out.println("Nome atual " + cliente.getName());
        
        System.out.print("Nome: ");
        cliente.setName(scanner.nextLine());
        
        // cliente.getTelefones().forEach(System.out::println);
       
        System.out.println("endereço atual" + cliente.getEndereco());

        Endereco endereco = new Endereco();

        System.out.print("Rua: ");
        endereco.setRua(scanner.nextLine());

        System.out.print("Bairro: ");
        endereco.setBairro(scanner.nextLine());

        System.out.print("Complemento: ");
        endereco.setComplemento(scanner.nextLine());

        System.out.print("Cidade: ");
        endereco.setCidade(scanner.nextLine());

        System.out.print("CEP: ");
        endereco.setCep(scanner.nextLine());

        System.out.print("UF: ");
        endereco.setUf(scanner.nextLine());

        cliente.setEndereco(endereco);

        clienteService.atualizarCliente(cliente);
    }

    private static void listarClientes(Scanner scanner, ClienteService clienteService) {
        try {
            List<Cliente> clientes = clienteService.buscarTodosClientes();
            if (clientes.isEmpty()) {
                System.out.println("Nenhum ticket encontrado.");
            } else {
                clientes.forEach(cliente -> System.out.println(
                        "CLENTE " + cliente.getId() + " Nome:  " + cliente.getName() + ", CPF : " + cliente.getCpf()));
            }
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
            // return List.of();
        }
    }

    private static void removerCliente(Scanner scanner, ClienteService clienteService) {
        System.out.print("Digite o CPF do cliente que deseja excluir: ");
        String cpf = scanner.nextLine();
        System.out.print("Tem certeza que deseja excluir o cliente com CPF " + cpf + "? (sim/não): ");
        String resposta = scanner.nextLine().trim().toLowerCase();

        if (resposta.equals("sim") || resposta.equals("s")) {
            try {
                clienteService.excluirCliente(cpf);
            } catch (Exception e) {
                System.out.println( e.getCause());
                System.out.println("Exclusão ERRO.");

            }
        }
    }

}
