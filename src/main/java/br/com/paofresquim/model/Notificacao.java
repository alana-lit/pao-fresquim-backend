package br.com.paofresquim.model;

import br.com.paofresquim.enums.StatusNotificacao;
import br.com.paofresquim.enums.TipoNotificacao;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notificacao")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoNotificacao tipo;

    @Column(columnDefinition = "TEXT")
    private String mensagem;

    @Column(name = "data_envio")
    private LocalDateTime dataEnvio;

    @Enumerated(EnumType.STRING)
    @Column
    private StatusNotificacao status;

}
