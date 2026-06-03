package br.com.paofresquim.integration;

import br.com.paofresquim.dto.request.NotificacaoRequest;
import br.com.paofresquim.dto.response.NotificacaoResponse;
import br.com.paofresquim.enums.StatusNotificacao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class SmsIntegration {

    private final RestTemplate restTemplate =
            new RestTemplate();

    @Value("${sms.api.key}")
    private String API_KEY;

    @Value("${sms.api.url}")
    private String URL;

    public NotificacaoResponse enviar(
            NotificacaoRequest request
    ) {

        LocalDateTime agora =
                LocalDateTime.now();

        try {

            HttpHeaders headers =
                    new HttpHeaders();

            headers.setContentType(
                    MediaType.APPLICATION_JSON
            );

            headers.set(
                    "Authorization",
                    API_KEY
            );

            Map<String, Object> body =
                    new HashMap<>();

            body.put(
                    "message",
                    request.getMensagem()
            );

            body.put(
                    "phoneNumbers",
                    new String[]{
                            request.getTelefone()
                    }
            );

            HttpEntity<Map<String, Object>> entity =
                    new HttpEntity<>(
                            body,
                            headers
                    );

            ResponseEntity<Map> response =
                    restTemplate.exchange(
                            URL,
                            HttpMethod.POST,
                            entity,
                            Map.class
                    );

            Map responseBody =
                    response.getBody();

            String gatewayId = null;

            if (responseBody != null
                    && responseBody.get("id") != null) {

                gatewayId =
                        responseBody
                                .get("id")
                                .toString();
            }

            return NotificacaoResponse.builder()
                    .gatewayId(gatewayId)
                    .telefone(request.getTelefone())
                    .mensagem(request.getMensagem())
                    .status(StatusNotificacao.ENVIADO)
                    .dataHoraEnvio(agora)
                    .httpStatus(
                            response.getStatusCode().value()
                    )
                    .respostaGateway(
                            responseBody != null
                                    ? responseBody.toString()
                                    : null
                    )
                    .build();

        } catch (HttpStatusCodeException e) {

            log.error(
                    "Erro HTTP SMS",
                    e
            );

            return NotificacaoResponse.builder()
                    .telefone(request.getTelefone())
                    .mensagem(request.getMensagem())
                    .status(StatusNotificacao.ERRO)
                    .dataHoraEnvio(agora)
                    .erro(e.getMessage())
                    .httpStatus(
                            e.getStatusCode().value()
                    )
                    .respostaGateway(
                            e.getResponseBodyAsString()
                    )
                    .build();

        } catch (Exception e) {

            log.error(
                    "Erro integração SMS",
                    e
            );

            return NotificacaoResponse.builder()
                    .telefone(request.getTelefone())
                    .mensagem(request.getMensagem())
                    .status(StatusNotificacao.FALHA)
                    .dataHoraEnvio(agora)
                    .erro(e.getMessage())
                    .httpStatus(500)
                    .respostaGateway(
                            e.getMessage()
                    )
                    .build();
        }
    }
}