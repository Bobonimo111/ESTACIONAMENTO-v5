package br.com.estacionamento.views;

import java.util.List;
import java.util.Scanner;

import br.com.estacionamento.model.Vaga;
import br.com.estacionamento.service.VeiculoService;

public class VagaView {
    public static void vagaView(Scanner scanner, VeiculoService veiculoService) {
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
                    cadastrarVaga(scanner, veiculoService);
                    break;
                case 2:
                    atualizarVaga(scanner, veiculoService);
                    break;
                case 3:
                    listarVagas(scanner, veiculoService);
                    break;
                case 4:
                    removerVaga(scanner, veiculoService);
                    break;
                case 0:
                    running = false;
                    System.out.println("Saindo do gerenciador de Vagas");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public static void cadastrarVaga(Scanner scanner, VeiculoService veiculoServico) {
        Vaga vaga = new Vaga();
        System.out.print("Andar da vaga [ex: 1]");
        vaga.setAndar(scanner.nextLine());
        System.out.print("Setor [ex: b]");
        vaga.setSetor(scanner.nextLine());
        System.out.print("Numero da vaga [15](Opcional):");
        Integer num = null;
        try {
            num = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            // System.out.println("Valor invalido");
        }

        if (num != null) {
            vaga.setNumero(num);
        } else {
            System.out.println("Numero não atribuido");
        }

        veiculoServico.adiconarVaga(vaga);
        System.out.println("Vaga adicionada ao sistema !");
    }

    public static void atualizarVaga(Scanner scanner, VeiculoService veiculoServico) {
        System.out.println("Id vaga :");
        Vaga vaga = veiculoServico.buscarVagaPorId((long) scanner.nextInt());
        scanner.nextLine();
        if (vaga == null) {
            System.out.println("Vaga não encontrada");
        } else {
            System.out.println("Andar da vaga: ");
            System.out.println("Andar = " + vaga.getAndar());
            vaga.setAndar(scanner.nextLine());

            System.out.println("Setor [ex: b]");
            System.out.println("Setor = " + vaga.getSetor());
            vaga.setSetor(scanner.nextLine());

            System.out.println("Numero da vaga [15](Opcional):");
            System.out.println("Num vaga = " + vaga.getNumero());
            Integer num = null;
            try {
                num = Integer.parseInt(scanner.nextLine());
                vaga.setNumero(num);
            } catch (Exception e) {
                // System.out.println("Valor invalido");
            }

            veiculoServico.editarVaga(vaga);
        }

    }

    public static void listarVagas(Scanner scanner, VeiculoService veiculoServico) {
        List<Vaga> vagas = veiculoServico.listarVagas();
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

    public static void removerVaga(Scanner scanner, VeiculoService veiculoServico) {
        System.out.println("Id vaga :");
        Vaga vaga = veiculoServico.buscarVagaPorId((long) scanner.nextInt());
        if (vaga == null) {
            System.out.println("Vaga não encontrada");
        } else {
            veiculoServico.removerVaga(vaga);
        }
    }
}
