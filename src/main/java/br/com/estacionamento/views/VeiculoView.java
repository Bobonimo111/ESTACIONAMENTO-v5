package br.com.estacionamento.views;

import java.util.List;
import java.util.Scanner;

import br.com.estacionamento.model.Cliente;
import br.com.estacionamento.model.Veiculo;
import br.com.estacionamento.service.ClienteService;
import br.com.estacionamento.service.VeiculoService;

public class VeiculoView {

    public static void veiculoView(Scanner scanner, VeiculoService veiculoService, ClienteService clienteService) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== SISTEMA DE Veiculos ===");

            System.out.println("1 - Cadastrar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Listar");
            System.out.println("4 - Listar por cpf");
            System.out.println("5 - Remover");

            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarVeiculo(scanner, veiculoService, clienteService);
                    break;
                case 2:
                    atualizarVeiculo(scanner, veiculoService);
                    break;
                case 3:
                    listarVeiculos(scanner, veiculoService);
                    break;
                case 4:
                    listarVeiculoPorCpf(scanner, veiculoService,clienteService);
                    break;
                case 5:
                    removerVeiculo(scanner, veiculoService);
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

    private static void cadastrarVeiculo(Scanner scanner, VeiculoService veiculoService,
            ClienteService clienteService) {
        Veiculo veiculo = new Veiculo();

        System.out.print("Digite a placa (7 caracteres): ");
        veiculo.setPlaca(scanner.nextLine());

        System.out.print("Digite o modelo (até 50 caracteres): ");
        veiculo.setModelo(scanner.nextLine());

        System.out.print("Digite a cor (até 30 caracteres): ");
        veiculo.setCor(scanner.nextLine());

        System.out.print("Digite a marca (até 50 caracteres): ");
        veiculo.setMarca(scanner.nextLine());

        System.out.print("Digite o ano (ex: 2020): ");
        try {
            veiculo.setAno(Integer.parseInt(scanner.nextLine()));
        } catch (NumberFormatException e) {
            System.out.println("Ano inválido. Por favor, insira um número inteiro.");
        }
        int tentativa = 0;
        do {
            tentativa++;
            System.out.print("Digite o CPF do cliente para associar ao veículo: ");
            Cliente cliente = clienteService.buscarClientePorCpf(scanner.nextLine());
            if (cliente == null) {
                System.out.println("Cliente não encontrado");
            } else {
                tentativa = 10;
                veiculo.setCliente(cliente);
            }
        } while (tentativa < 3);

        veiculoService.cadastrarVeiculo(veiculo);
    }

    private static void atualizarVeiculo(Scanner scanner, VeiculoService veiculoService) {
        System.out.println("Busca veiculo pela placa :");
        Veiculo veiculo = veiculoService.buscarVeiculoPorPlaca(scanner.nextLine());
        if (veiculo == null) {
            System.out.println("Veiculo não encontrado no sistema");
        } else {
            System.out.println(veiculo);
            // "Veiculo {\n" +
            // " Placa: " + placa + "\n" +
            // " Modelo: " + modelo + "\n" +
            // " Cor: " + cor + "\n" +
            // " Marca: " + marca + "\n" +
            // " Ano: " + ano + "\n" +
            // "}"

            System.out.print("Digite a placa (7 caracteres): ");
            veiculo.setPlaca(scanner.nextLine());

            System.out.print("Digite o modelo (até 50 caracteres): ");
            veiculo.setModelo(scanner.nextLine());

            System.out.print("Digite a cor (até 30 caracteres): ");
            veiculo.setCor(scanner.nextLine());

            System.out.print("Digite a marca (até 50 caracteres): ");
            veiculo.setMarca(scanner.nextLine());

            System.out.print("Digite o ano (ex: 2020): ");
            try {
                veiculo.setAno(Integer.parseInt(scanner.nextLine()));
            } catch (NumberFormatException e) {
                System.out.println("Ano inválido. Por favor, insira um número inteiro.");
            }

            veiculoService.atualizarVeiculo(veiculo);
        }

    }

    private static void listarVeiculos(Scanner scanner, VeiculoService veiculoService) {
        veiculoService.listarVeiculo().forEach(veiculo -> System.out.println(veiculo));
    }

    private static void listarVeiculoPorCpf(Scanner scanner, VeiculoService veiculoService,ClienteService clienteService) {

        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();

        Cliente cliente = clienteService.buscarClientePorCpf(cpf);
        List<Veiculo> veiculos = veiculoService.buscarVeiculoPorCpf(cpf);

        if (veiculos == null || veiculos.isEmpty()) {
            System.out.println("Nenhum veículo encontrado para o CPF informado.");
        } else {
            System.out.println("Veículos encontrados no nome de  "+ cliente.getName());
            for (Veiculo v : veiculos) {
                System.out.println(v);
            }
        }
    }

    private static void removerVeiculo(Scanner scanner, VeiculoService veiculoService) {
        System.out.println("Qual a placa do veiculo que deseja remover");

        Veiculo veiculo = veiculoService.buscarVeiculoPorPlaca(scanner.nextLine());

        if (veiculo == null) {
            System.out.println("veiculo não encontrado no sistema");
        } else {
            veiculoService.removerVeiculo(veiculo.getPlaca());
            System.out.println("Veiculo removido do sistema");
        }

    }

}
