package br.com.estacionamento.model;

import java.time.LocalDate;

import jakarta.persistence.Embedded;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String cpf;

    private String telefone;

    private LocalDate dataNasc;

    @Embedded
    private Endereco endereco;

    public abstract String getTipo();

    @Override
    public String toString() {
        return getTipo() + " {\n" +
                "  ID: " + id + "\n" +
                "  Nome: " + name + "\n" +
                "  CPF: " + cpf + "\n" +
                "  Telefone: " + telefone + "\n" +
                "  Data de Nascimento: " + dataNasc + "\n" +
                "}";
    }

}
