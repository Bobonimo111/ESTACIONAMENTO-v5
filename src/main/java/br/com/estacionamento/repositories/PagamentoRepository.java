package br.com.estacionamento.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.estacionamento.model.Pagamento;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento,Long> {

    @Query("SELECT pag FROM Pagamento pag WHERE pag.ticket.id = :id")
    Pagamento buscarPagamentos(@Param("id") Long id);
    
}
