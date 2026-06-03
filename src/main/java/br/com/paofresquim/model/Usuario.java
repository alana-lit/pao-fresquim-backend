package br.com.paofresquim.model;

import br.com.paofresquim.enums.NivelAcesso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_funcionario")
    private Integer idFuncionario;

    private String usuario;

    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_acesso")
    private NivelAcesso nivelAcesso;
}