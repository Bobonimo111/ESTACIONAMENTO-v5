package br.com.estacionamento;

import br.com.estacionamento.service.*;
import br.com.estacionamento.views.ClienteView;
import br.com.estacionamento.views.RelatorioView;
import br.com.estacionamento.views.TicketView;
import br.com.estacionamento.views.VagaView;
import br.com.estacionamento.views.VeiculoView;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class EstacionamentoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EstacionamentoApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(ClienteService clienteService, VeiculoService veiculoService,
            FuncionarioService funcionarioService, TicketService ticketService) {
        return args -> {
            Scanner scanner = new Scanner(System.in);
            boolean running = true;

            while (running) {
                System.out.println("\n=== SISTEMA DE ESTACIONAMENTO ===");
                // Funcionario -> cliente
                // Funcionario -> Ticket
                // Funcionario -> Vagas
                // Funcionario -> Relatorios
                // Funcionario -> Veiculos

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
                        ClienteView.clienteView(scanner, clienteService);
                        break;
                    case 2:
                        String senha = "1234";
                        System.out.print("Entre a senha: ");
                        if (senha.equals(scanner.nextLine())) {
                            TicketView.ticketView(scanner, ticketService, veiculoService, null);
                        }else{
                            System.out.println("Senha invalida");
                            System.out.println("Acesso negado...");
                        }
                        break;
                    case 3:
                        VagaView.vagaView(scanner,veiculoService);
                        break;
                    case 4:
                        RelatorioView.relatorioView(scanner,ticketService,veiculoService,clienteService,funcionarioService);
                        break;
                    case 5:
                        VeiculoView.veiculoView(scanner,veiculoService,clienteService);
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
}
