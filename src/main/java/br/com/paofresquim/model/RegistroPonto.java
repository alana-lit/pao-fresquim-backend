package br.com.paofresquim.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "registro_ponto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroPonto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_funcionario", nullable = false)
    private Funcionario funcionario;

    @Column(name = "data_registro", nullable = false)
    private LocalDate dataRegistro;

    @Column
    private LocalTime horario;

    @Column
    private Boolean feriado = false;

    @Column
    private Boolean falta = false;
}