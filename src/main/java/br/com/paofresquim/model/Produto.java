package br.com.paofresquim.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "produto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private Integer codigo;

    @Column(name = "nome_produto", nullable = false, length = 100)
    private String nomeProduto;

    @Column(name = "data_fabricacao")
    private Date dataFabricacao;

    @Column(name = "data_validade")
    private Date dataValidade;

    @Column(nullable = false, length = 100)
    private String lote;

    @Column(name = "codigo_barra", length = 50, unique = true)
    private String codigoBarra;

    @Column(length = 50)
    private String categoria;

    @Column(length = 100)
    private String alergicos;

    @Column(length = 255)
    private String descricao;

    @Column(name = "preco_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoUnitario;
}