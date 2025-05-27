package br.com.estacionamento.service;

import java.util.List;

import br.com.estacionamento.model.Cliente;

public interface ClienteService {
    void cadastrarCliente(Cliente cliente);

    List<Cliente> buscarTodosClientes();

    Cliente buscarClientePorCpf(String cpf);

    void atualizarCliente(Cliente cliente);

    void excluirCliente(String cpf);
}
