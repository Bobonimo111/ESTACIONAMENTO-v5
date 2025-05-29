package br.com.estacionamento.repositories;

import br.com.estacionamento.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    Cliente findByCpf(String cpf);
    boolean deleteByCpf(String cpf);

   
}
