package br.com.paofresquim.model;

import br.com.paofresquim.enums.EstadoCivil;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "pessoa")
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 14, unique = true)
    private String cpf;

    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @Column(nullable = false, length = 150)
    private String email;

    @Column(length = 15)
    private String telefone;

    @Column(length = 150)
    private String endereco;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_civil")
    private EstadoCivil estadoCivil;
}