package br.com.estacionamento.service;

import java.util.List;

import br.com.estacionamento.model.Funcionario;

public interface FuncionarioService {
    void cadastrarFuncionario(Funcionario funcionario);
    Funcionario buscarFuncionarioPorCpf(String cpf);
    void atualizarFuncionario(Funcionario funcionario);
    void excluirFuncionario(String cpf);
    List<Funcionario> listAll();
}
