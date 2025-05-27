package br.com.estacionamento.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vagas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero", unique = true, nullable = false)
    private Integer numero;

    @Column(name = "setor", length = 50, nullable = false)
    private String setor;

    @Column(name = "andar", length = 20, nullable = false)
    private String andar;

    @Column(name = "disponivel", nullable = false)
    @Builder.Default
    private Boolean disponivel = true;

    public boolean reservar() {
        if (this.disponivel) {
            this.disponivel = false;
            return true;
        }
        return false;
    }

    public void liberar() {
        this.disponivel = true;
    }
}