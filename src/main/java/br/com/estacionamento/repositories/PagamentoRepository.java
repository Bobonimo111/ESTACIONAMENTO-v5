package br.com.estacionamento.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.estacionamento.model.Pagamento;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento,Long> {
    
}
