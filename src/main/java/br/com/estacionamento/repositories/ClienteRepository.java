package br.com.estacionamento.repositories;

import br.com.estacionamento.model.Cliente;
import br.com.estacionamento.model.Veiculo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    Cliente findByCpf(String cpf);
    boolean deleteByCpf(String cpf);

   
}
