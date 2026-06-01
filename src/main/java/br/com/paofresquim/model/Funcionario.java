package br.com.paofresquim.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "funcionario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {

    @Id
    @Column(name = "id")
    private Integer id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Pessoa pessoa;

    @Column(name = "data_contratacao")
    private LocalDate dataContratacao;

    @Column(precision = 10, scale = 2)
    private BigDecimal salario;

    @Column(name = "data_fim_salario")
    private LocalDate dataFimSalario;

    @Column(name = "ferias_inicio")
    private LocalDate feriasInicio;

    @Column(name = "ferias_fim")
    private LocalDate feriasFim;

    @Column(name = "pis_pasep", length = 20)
    private String pisPasep;

    @Column(length = 80)
    private String cargo;

    @Column(unique = true)
    private Integer matricula;

    @Column(name = "contato_emergencia", length = 14)
    private String contatoEmergencia;

    @Column(name = "nome_contato_emergencia", length = 90)
    private String nomeContatoEmergencia;
}