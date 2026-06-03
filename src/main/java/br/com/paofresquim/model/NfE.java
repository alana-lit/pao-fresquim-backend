package br.com.paofresquim.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "nf_e")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NfE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_venda", nullable = false, unique = true)
    private Venda venda;

    @Column(length = 30)
    private String estado;

    @Column(name = "data_nf")
    private LocalDate dataNf;

    @Column
    private LocalTime hora;

    @Column(length = 150)
    private String cnpj;

    @Column(name = "nome_fantasia", length = 200)
    private String nomeFantasia;

    @Column(name = "nome_empresarial", length = 200)
    private String nomeEmpresarial;

    @Column(precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(length = 14)
    private String cpf;

    @Column(name = "chave_acesso", length = 44)
    private String chaveAcesso;
}
