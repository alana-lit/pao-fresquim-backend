package br.com.paofresquim.model;

import br.com.paofresquim.enums.MeioPagamento;
import br.com.paofresquim.enums.StatusVenda;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "pagamento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_venda", nullable = false)
    private Venda venda;

    @Enumerated(EnumType.STRING)
    @Column(name = "meio_pagamento")
    private MeioPagamento meioPagamento;

    @Column(name = "valor_pagamento", precision = 10, scale = 2)
    private BigDecimal valorPagamento;

    @Column(name = "data_pagamento")
    private Date dataPagamento;
}

