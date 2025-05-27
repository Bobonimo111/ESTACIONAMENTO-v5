package br.com.estacionamento.service.impl;

import br.com.estacionamento.model.Ticket;
import br.com.estacionamento.repositories.TicketRepository;
import br.com.estacionamento.service.ClienteService;
import br.com.estacionamento.service.TicketService;
import br.com.estacionamento.service.VeiculoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final VeiculoService veiculoService;
    private final ClienteService clienteService;

    @Override
    @Transactional
    public void abrirTicket(Ticket ticket) {
        this.ticketRepository.save(ticket);
    }

    @Override
    public Ticket buscarTicketPorId(Long id) {
        var ticket = this.ticketRepository.findById(id);
        if (ticket.isPresent()) {
            return ticket.get();
        } else {
            throw new RuntimeException("Ticket não encontrado com o ID: " + id);
        }
    }

    @Override
    public List<Ticket> buscarTicketPorCpf(String cpf) {
        var cliente = this.clienteService.buscarClientePorCpf(cpf);
        var tickets = this.ticketRepository.findByCliente(cliente);

        if (tickets == null || tickets.isEmpty()) {
            return Collections.emptyList();
        }

        return tickets;
    }

    @Override
    public List<Ticket> buscarTodosTickets() {
        return this.ticketRepository.findAll();
    }

    @Override
    public Ticket buscarTicketPorVeiculo(String placa) {
        var veiculo = this.veiculoService.buscarVeiculoPorPlaca(placa);
        var ticket = this.ticketRepository.findByVeiculo(veiculo);

        if (ticket == null) {
            throw new RuntimeException("Ticket não encontrado para o veículo com a placa: " + placa);
        }

        return ticket;
    }

    @Override
    public void fecharTicket(Long id) {
        this.ticketRepository.deleteById(id);
    }
}
