package br.com.estacionamento.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

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

    @Column(name = "valor", nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(name = "tempo_permanencia", nullable = false)
    private Integer tempoPermanencia;

    @Column(name = "hora_saida")
    private LocalDateTime horaSaida;

    @OneToOne(mappedBy = "ticket")
    private Pagamento pagamento;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculo veiculo;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;
}