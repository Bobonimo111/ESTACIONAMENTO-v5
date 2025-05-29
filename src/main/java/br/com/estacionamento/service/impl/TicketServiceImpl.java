package br.com.estacionamento.service.impl;

import br.com.estacionamento.model.Pagamento;
import br.com.estacionamento.model.Ticket;
import br.com.estacionamento.repositories.PagamentoRepository;
import br.com.estacionamento.repositories.TicketRepository;
import br.com.estacionamento.service.ClienteService;
import br.com.estacionamento.service.TicketService;
import br.com.estacionamento.service.VeiculoService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final PagamentoRepository pagamentoRepository;
    private final VeiculoService veiculoService;
    private final ClienteService clienteService;
    @Autowired
    private EntityManager manager;

    @Override
    @Transactional
    public Ticket abrirTicket(Ticket ticket) {
        return this.ticketRepository.save(ticket);
    }

    @Override
    @Transactional
    public Ticket buscarTicketPorId(Long id) {
        var ticket = this.ticketRepository.findById(id);
        if (ticket.isPresent()) {
            return ticket.get();
        } else {
            return null;
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
            System.out.println("Ticket não encontrado para o veículo com a placa: " + placa);
        }

        return ticket;
    }

    @Override
    public void fecharTicket(Long id) {
        this.pagamentoRepository.deleteById(id);
        ;
        this.ticketRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void gerarPagamento(Pagamento pagamento, Ticket ticket) {
        manager.merge(ticket);  
        if (manager.contains(ticket)) {
            System.out.println("✅ Ticket está gerenciado (em contexto de persistência).");
        } else {
            System.out.println("❌ Ticket está detached (fora do contexto).");
        }
        this.pagamentoRepository.save(pagamento);
    }
    @Override
    @Transactional
    public void atualizarTicket(Ticket ticket) {
        this.ticketRepository.save(ticket);
    }

    @Override
    public Pagamento buscarPagmentoPorTicketId(Long id) {
        return pagamentoRepository.buscarPagamentos(id);
    }

}
