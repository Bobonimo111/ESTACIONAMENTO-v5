package br.com.estacionamento.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "clientes")
public class Cliente extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    private String name;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Builder.Default
    private List<Veiculo> veiculos = new java.util.ArrayList<>();

    @OneToMany(mappedBy = "cliente")
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ClienteTelefone> telefones;

    
    private boolean ativo;

    @Column(columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private LocalDate dataCadastro;


    @PrePersist
    public void prePersist() {
        this.dataCadastro = LocalDate.now();
    } 

    public void adicionarVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
        veiculo.setCliente(this);
    }

    public void adicionarTelefone(ClienteTelefone telefone) {
        this.telefones.add(telefone);
        telefone.setCliente(this);
    }

    @Override
    public String getTipo() {
        return "CLIENTE";
    }

    @Override
    public String toString() {
        return "Cliente [id=" + id + ", name=" + name + ", endereco=" + super.getEndereco() + ", ativo=" + ativo + ", data_cadastro="
                + dataCadastro + "]";
    }

    
}