package br.com.estacionamento.service.impl;

import br.com.estacionamento.model.Vaga;
import br.com.estacionamento.model.Veiculo;
import br.com.estacionamento.repositories.VeiculoRepository;
import br.com.estacionamento.repositories.ClienteRepository;
import br.com.estacionamento.repositories.VagaRepository;
import br.com.estacionamento.service.VeiculoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VeiculoServiceImpl implements VeiculoService {

    private final ClienteRepository clienteRepository;

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
        this.veiculoRepository.save(veiculo);
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

    
    @Override
    @Transactional
    public Vaga buscarVagaPorId(Long id) {
        return vagaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Veiculo> listarVeiculo() {
        return veiculoRepository.findAll();
    }

    @Override
    public List<Veiculo> buscarVeiculoPorCpf(String cpf) {
       return veiculoRepository.findVeiculos(cpf);
    }    
    
}
