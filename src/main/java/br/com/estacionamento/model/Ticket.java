package br.com.estacionamento.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column(name = "valor", nullable = false, precision = 10, scale = 2)
    // private BigDecimal valor;

    @Column(name = "tempo_permanencia", nullable = false)
    private Integer tempoPermanencia;

    @Column(name = "hora_saida")
    private LocalDateTime horaSaida;

    @OneToOne(mappedBy = "ticket",cascade = CascadeType.PERSIST)
    private Pagamento pagamento;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = true)
    private Veiculo veiculo;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @Override
    public String toString() {
    return "ClasseNome {" +
           "\n  id=" + id +
           ",\n  tempoPermanencia=" + tempoPermanencia +
           ",\n  horaSaida=" + horaSaida +
           ",\n  pagamento=" + pagamento +
           ",\n  cliente=" + (cliente == null ? cliente.getName() : "null") +
           ",\n  veiculo=" + (veiculo == null ? veiculo.getModelo() : "null") +
           ",\n  funcionario=" + funcionario == null ? funcionario.getName() : "null" +
           "\n}";
}

    
}