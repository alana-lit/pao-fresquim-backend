package br.com.paofresquim.model;

import br.com.paofresquim.enums.StatusVenda;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "venda")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_funcionario", nullable = false)
    private Funcionario funcionario;

    @Column(name = "data_compra")
    private LocalDateTime dataCompra;

    @Column(precision = 10, scale = 2)
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_venda")
    private StatusVenda StatusVenda;

    @Column(name = "ultima_cobranca")
    private LocalDateTime ultimaCobranca;
}
