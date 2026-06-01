package br.com.paofresquim.model;

import br.com.paofresquim.enums.NivelAcesso;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_funcionario", nullable = false, unique = true)
    private Funcionario funcionario;

    @Column(nullable = false, length = 255)
    private String senha;

    @Column(nullable = false, length = 80, unique = true)
    private String usuario;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_acesso", nullable = false)
    private NivelAcesso nivelAcesso;

}