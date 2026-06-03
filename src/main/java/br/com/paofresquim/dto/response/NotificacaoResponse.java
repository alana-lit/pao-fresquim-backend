package br.com.paofresquim.dto.response;

import br.com.paofresquim.enums.StatusNotificacao;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NotificacaoResponse {

    private String gatewayId;
    private String telefone;
    private String mensagem;
    private StatusNotificacao status;
    private LocalDateTime dataHoraEnvio;
    private String erro;
    private Integer httpStatus;
    private String respostaGateway;
}