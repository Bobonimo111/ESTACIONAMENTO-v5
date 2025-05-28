package br.com.estacionamento.service.impl;

import br.com.estacionamento.model.Funcionario;
import br.com.estacionamento.repositories.FuncionarioRepository;
import br.com.estacionamento.service.FuncionarioService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FuncionarioServiceImpl implements FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    @Override
    @Transactional
    public void cadastrarFuncionario(Funcionario funcionario) {
        this.funcionarioRepository.save(funcionario);
    }

    

    @Override
    public List<Funcionario> listAll() {
        return funcionarioRepository.findAll();
    }



    @Override
    public Funcionario buscarFuncionarioPorCpf(String cpf) {
        var funcionario = this.funcionarioRepository.findByCpf((cpf));
        if (funcionario == null) {
            throw new RuntimeException("Funcionário não encontrado com o CPF: " + cpf);
        }

        return funcionario;
    }

    @Override
    @Transactional
    public void atualizarFuncionario(Funcionario funcionario) {
        var funcionarioExistente = buscarFuncionarioPorCpf(funcionario.getCpf());

        if (funcionarioExistente.getName() != funcionario.getName()) {
            funcionarioExistente.setName(funcionario.getName());
        }

        if (funcionarioExistente.getTelefone() != funcionario.getTelefone()) {
            funcionarioExistente.setTelefone(funcionario.getTelefone());
        }

        this.funcionarioRepository.save(funcionarioExistente);
    }

    @Override
    public void excluirFuncionario(String cpf) {
        this.funcionarioRepository.deleteByCpf((cpf));
    }
}
