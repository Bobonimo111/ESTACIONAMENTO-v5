package br.com.estacionamento.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "pagamentos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_pagamento")
    private LocalDateTime dataPagamento;

    @Column(name = "valor", precision = 10, scale = 2)
    private BigDecimal valor;

    @OneToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @Override
    public String toString() {
        return "Pagamento {" +
                "\n  id=" + id +
                ",\n  dataPagamento=" + dataPagamento +
                ",\n  valor=" + valor +
                ",\n  ticket=" + ticket +
                "\n}";
    }

}
