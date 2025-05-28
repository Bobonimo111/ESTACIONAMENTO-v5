package br.com.estacionamento.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Endereco {

    private String rua;
    private String bairro;
    private String complemento;
    private String cidade;
    private String cep;
    private String uf;
}
