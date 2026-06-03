package br.com.paofresquim.model;

import br.com.paofresquim.enums.StatusNotificacao;
import br.com.paofresquim.enums.TipoNotificacao;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notificacao")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    private TipoNotificacao tipo;

    @Column
    private Boolean cobranca;

    @Column(columnDefinition = "TEXT")
    private String mensagem;

    @Column(name = "data_envio")
    private LocalDateTime dataEnvio;

    @Enumerated(EnumType.STRING)
    private StatusNotificacao status;

    @Column(
            name = "gateway_id",
            columnDefinition = "TEXT"
    )
    private String gatewayId;

    @Column(columnDefinition = "TEXT")
    private String erro;

    @Column(name = "http")
    private Integer httpStatus;

    @Column(name = "resposta_gateway",
            columnDefinition = "TEXT")
    private String respostaGateway;
}