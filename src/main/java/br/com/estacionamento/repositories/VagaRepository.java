package br.com.estacionamento.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.estacionamento.model.Vaga;

@Repository
public interface VagaRepository extends JpaRepository<Vaga,Long>{
    @Query("Select v from Vaga v where v.setor = :setor AND v.andar = :setor")
    public List<Vaga> buscarPorAndarSetor(@Param("andar")String andar,@Param("setor") String setor);
}
