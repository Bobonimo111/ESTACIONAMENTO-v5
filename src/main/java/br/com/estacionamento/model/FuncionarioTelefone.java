package br.com.estacionamento.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "funcionario_telefone")
public class FuncionarioTelefone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario cliente;

    @Override
    public String toString() {
        return "Funcionario Telefone [numero=" + numero + "]";
    }

    
}
