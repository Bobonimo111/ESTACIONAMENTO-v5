package br.com.estacionamento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.estacionamento.model.Vaga;

@Repository
public interface VagaRepository extends JpaRepository<Vaga,Long>{
    
}
