package br.com.estacionamento.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "funcionarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Funcionario extends Pessoa {

    @Column(name = "pis", unique = true, length = 14, nullable = false)
    private String pis;

    @Column(name = "cargo", nullable = false, length = 50)
    private String cargo;

    @Column(name = "salario", precision = 10, scale = 2, nullable = false)
    private BigDecimal salario;

    @Column(name = "data_admissao", nullable = false)
    private LocalDate dataAdmissao;

    @Override
    public String getTipo() {
        return "FUNCIONARIO";
    }

    @Override
    public String toString() {
        return "Funcionario {\n" +
                "  PIS: " + pis + "\n" +
                "  Cargo: " + cargo + "\n" +
                "  Salário: R$ " + salario + "\n" +
                "  Data de Admissão: " + dataAdmissao + "\n" +
                "  Endereço: " + super.getEndereco() + "\n" +
                "}";
    }

}
