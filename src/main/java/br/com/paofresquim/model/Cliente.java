package br.com.paofresquim.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cliente")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @Column(name = "id_pessoa")
    private Integer id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @Column(name = "estado_serasa")
    private Boolean estadoSerasa = false;

    @Column(name = "inativo")
    private Boolean inativo = false;
}