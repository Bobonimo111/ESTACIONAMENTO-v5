package br.com.estacionamento.service;

import java.util.List;

import br.com.estacionamento.model.Vaga;
import br.com.estacionamento.model.Veiculo;

public interface VeiculoService {
    void  cadastrarVeiculo(Veiculo veiculo);
    Veiculo buscarVeiculoPorPlaca(String placa);
    void atualizarVeiculo(Veiculo veiculo);
    void removerVeiculo(String placa);

    void adiconarVaga(Vaga vaga);
    void editarVaga(Vaga vaga);
    List<Vaga> listarVagas();
    void removerVaga(Vaga vaga);
}
