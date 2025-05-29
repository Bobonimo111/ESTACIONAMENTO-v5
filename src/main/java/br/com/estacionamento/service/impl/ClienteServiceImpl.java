package br.com.estacionamento.service.impl;

import br.com.estacionamento.model.Cliente;
import br.com.estacionamento.repositories.ClienteRepository;
import br.com.estacionamento.service.ClienteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    @Transactional
    public void cadastrarCliente(Cliente cliente) {
        this.clienteRepository.save(cliente);
    }

    @Override
    public Cliente buscarClientePorCpf(String cpf) {
        var cliente = this.clienteRepository.findByCpf(cpf);
        if (cliente == null) {
            return null;
        }

        return cliente;
    }

    @Override
    @Transactional
    public void atualizarCliente(Cliente cliente) {
        this.clienteRepository.save(cliente);
    }

    @Override
    @Transactional
    public void excluirCliente(String cpf) {
        Cliente cliente = this.clienteRepository.findByCpf(cpf);
        if (cliente != null) {
            this.clienteRepository.delete(cliente);
            // return true;
        }
        // return false;
    }

    @Override
    public List<Cliente> buscarTodosClientes() {
          return this.clienteRepository.findAll();
    }

    
}
