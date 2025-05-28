package br.com.estacionamento.repositories;

import br.com.estacionamento.model.Veiculo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    Veiculo findByPlaca(String placa);
    void deleteByPlaca(String placa);

    @Query("SELECT vei FROM Veiculo vei WHERE vei.cliente.cpf = :cpf")
    List<Veiculo> findVeiculos(@Param("cpf") String cpf);
}
