package br.com.estacionamento.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "veiculos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "placa")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "placa", length = 7, unique = true, nullable = false)
    private String placa;

    @Column(name = "modelo", nullable = false, length = 50)
    private String modelo;

    @Column(name = "cor", nullable = false, length = 30)
    private String cor;

    @Column(name = "marca", nullable = false, length = 50)
    private String marca;

    @Column(name = "ano", nullable = false)
    private Integer ano;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToOne()
    @JoinColumn(name = "vaga_id")
    private Vaga vaga;

    @OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Ticket> tickets = new ArrayList<>();

    @Override
    public String toString() {
        return "Veiculo {\n" +
                "  Placa: " + placa + "\n" +
                "  Modelo: " + modelo + "\n" +
                "  Cor: " + cor + "\n" +
                "  Marca: " + marca + "\n" +
                "  Ano: " + ano + "\n" +
                "}";
    }

    public void adicionarTicket(Ticket ticket) {
        tickets.add(ticket);
        ticket.setVeiculo(this);
    }
}