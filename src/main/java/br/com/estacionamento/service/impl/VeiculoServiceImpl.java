package br.com.estacionamento.service.impl;

import br.com.estacionamento.model.Vaga;
import br.com.estacionamento.model.Veiculo;
import br.com.estacionamento.repositories.VeiculoRepository;
import br.com.estacionamento.repositories.VagaRepository;
import br.com.estacionamento.service.VeiculoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VeiculoServiceImpl implements VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final VagaRepository vagaRepository;

    @Override
    @Transactional
    public void cadastrarVeiculo(Veiculo veiculo) {
        this.veiculoRepository.save(veiculo);
    }

    @Override
    public Veiculo buscarVeiculoPorPlaca(String placa) {
        var veiculo = this.veiculoRepository.findByPlaca((placa));

        if (veiculo == null) {
            return null;
        }

        return veiculo;
    }

    @Override
    @Transactional
    public void atualizarVeiculo(Veiculo veiculo) {
        var veiculoExistente = buscarVeiculoPorPlaca(veiculo.getPlaca());

        if (veiculo.getMarca() != null) {
            veiculoExistente.setMarca(veiculo.getMarca());
        }

        if (veiculo.getModelo() != null) {
            veiculoExistente.setModelo(veiculo.getModelo());
        }

        if (veiculo.getCor() != null) {
            veiculoExistente.setCor(veiculo.getCor());
        }

        this.veiculoRepository.save(veiculoExistente);
    }

    @Override
    public void removerVeiculo(String placa) {
        this.veiculoRepository.deleteByPlaca(placa);
    }

    @Override
    @Transactional
    public void adiconarVaga(Vaga vaga) {
        this.vagaRepository.save(vaga);
    }

    @Override
    @Transactional
    public void editarVaga(Vaga vaga) {
        this.vagaRepository.save(vaga);
    }

    @Override
    public List<Vaga> listarVagas() {
        return vagaRepository.findAll();
    }

    @Override
    @Transactional
    public void removerVaga(Vaga vaga) {
        vagaRepository.delete(vaga);   
    }

    
}
