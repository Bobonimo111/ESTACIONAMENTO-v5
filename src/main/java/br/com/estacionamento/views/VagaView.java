package br.com.estacionamento.views;

import java.util.Scanner;

import br.com.estacionamento.service.VeiculoService;

public class VagaView {
    public static void vagaView(Scanner scanner,VeiculoService veiculoService){
        boolean running = true;
        while (running) {
            System.out.println("\n=== SISTEMA DE Vagas ===");

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
                    cadastrarTicket(scanner, veiculoService);
                    break;
                case 2:
                    atualizarTicket(scanner, veiculoService);
                    break;
                case 3:
                    listarTickets(scanner, veiculoService);
                    break;
                case 4:
                    removerTicket(scanner, veiculoService);
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

    public static void cadastrar(){

    }
    public static void atualizar(){
        
    }
    public static void lista(){
        
    }
    public static void remover(){
        
    }
}
