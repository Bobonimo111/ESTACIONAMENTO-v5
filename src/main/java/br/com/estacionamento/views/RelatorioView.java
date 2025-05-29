package br.com.estacionamento.views;

import java.util.List;
import java.util.Scanner;

import br.com.estacionamento.model.Cliente;
import br.com.estacionamento.model.Funcionario;
import br.com.estacionamento.model.Ticket;
import br.com.estacionamento.model.Vaga;
import br.com.estacionamento.model.Veiculo;
import br.com.estacionamento.service.ClienteService;
import br.com.estacionamento.service.FuncionarioService;
import br.com.estacionamento.service.TicketService;
import br.com.estacionamento.service.VeiculoService;

public class RelatorioView {
    public static void relatorioView(Scanner scanner, TicketService ticketService, VeiculoService veiculoService,
           ClienteService clienteService,FuncionarioService funcionarioService) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== SISTEMA DE RELATORIOS ===");

            System.out.println("1 - Clientes e veiculos");
            System.out.println("2 - Tickets e pagamentos");
            System.out.println("3 - Funcionarios no sistemas");
            System.out.println("4 - Vagas disponiveis");

            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    clienteVeiculos(scanner, clienteService, veiculoService);
                    break;
                case 2:
                    ticketPagamentos(scanner, ticketService);
                    break;
                case 3:
                    funcionarios(scanner, funcionarioService);
                    break;
                case 4:
                    vagasDisponiveis(scanner, veiculoService);
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

    private static void vagasDisponiveis(Scanner scanner, VeiculoService veiculoService) {
        List<Vaga> vagas = veiculoService.listarVagas();
        for(Vaga v : vagas){
            if(v.getDisponivel()){
                System.out.println("==================");
                System.out.println("Andar : "+v.getAndar());
                System.out.println("Setor : "+v.getSetor());
                System.out.println("Numero : "+v.getNumero());
                System.out.println("Disponivel : "+ (v.getDisponivel() ? "Sim" : "Não") );
            }
        }
    }

    private static void funcionarios(Scanner scanner, FuncionarioService funcionarioService) {
        List<Funcionario> funcionarios = funcionarioService.listAll();
        if(funcionarios.isEmpty()){
            System.out.println("Nenhum funcionario no sistema");
        }else{
            for(Funcionario f: funcionarios){
                System.out.println(f);
            }
        }
    }

    private static void ticketPagamentos(Scanner scanner, TicketService ticketService) {
        List<Ticket> tickets = ticketService.buscarTodosTickets();
        for(Ticket t : tickets){
            System.out.println("=============================");
            System.out.println("Ticket numero : "+t.getId());
            System.out.println("Permanencia horas : "+ t.getTempoPermanencia());
            System.out.println("Saida : "+t.getHoraSaida());
            System.out.println("Cliente : "+ t.getCliente().getName());
            System.out.println("Valor : " + t.getTempoPermanencia() * 7.50);
        }
    }

    private static void clienteVeiculos(Scanner scanner, ClienteService clienteService, VeiculoService veiculoService) {
        for (Cliente cliente : clienteService.buscarTodosClientes()){
            System.out.println("================================");
            System.out.println("Nome : "+ cliente.getName());
            List<Veiculo> veiculos = cliente.getVeiculos();
            System.out.println("Numero de veiculos : "+veiculos.size());
            System.out.println("==================");          
            for(Veiculo v : veiculos){
                System.err.println(" Marca :"+v.getMarca());
                System.err.println(" Modelo :"+v.getModelo());
                System.err.println(" Placa  :"+v.getPlaca());
                System.err.println(" Ano :"+v.getAno());
                System.out.println("==================");   
            }
            
        }
    }
}
