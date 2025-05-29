package br.com.estacionamento.service;

import java.util.List;

import br.com.estacionamento.model.Vaga;
import br.com.estacionamento.model.Veiculo;

public interface VeiculoService {
    void  cadastrarVeiculo(Veiculo veiculo);
    List<Veiculo> buscarVeiculoPorCpf(String cpf);
    Veiculo buscarVeiculoPorPlaca(String placa);
    void atualizarVeiculo(Veiculo veiculo);
    void removerVeiculo(Veiculo veiculo);
    List<Veiculo> listarVeiculo();

    void adiconarVaga(Vaga vaga);
    void editarVaga(Vaga vaga);
    List<Vaga> listarVagas();
    void removerVaga(Vaga vaga);
    Vaga buscarVagaPorId(Long id);
    List<Vaga> buscarVagasPorSetorAndar(String andar,String setor);
}
