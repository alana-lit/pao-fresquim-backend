package br.com.paofresquim.integration;

import br.com.paofresquim.dto.request.WhatsappRequest;
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
public class WhatsappIntegration {

    private final RestTemplate restTemplate =
            new RestTemplate();

    @Value("${evolution.api.url}")
    private String evolutionApiUrl;

    @Value("${evolution.api.key}")
    private String apiKey;

    @Value("${evolution.dispatch.path}")
    private String dispatchPath;

    public NotificacaoResponse enviar(
            WhatsappRequest request
    ) {

        LocalDateTime agora =
                LocalDateTime.now();

        try {

            String url =
                    evolutionApiUrl +
                            dispatchPath;

            HttpHeaders headers =
                    new HttpHeaders();

            headers.setContentType(
                    MediaType.APPLICATION_JSON
            );

            headers.set(
                    "apikey",
                    apiKey
            );

            Map<String, Object> body =
                    new HashMap<>();

            body.put(
                    "number",
                    request.getTelefone()
            );

            body.put(
                    "text",
                    request.getMensagem()
            );

            HttpEntity<Map<String, Object>> entity =
                    new HttpEntity<>(
                            body,
                            headers
                    );

            ResponseEntity<Map> response =
                    restTemplate.exchange(
                            url,
                            HttpMethod.POST,
                            entity,
                            Map.class
                    );

            Map responseBody =
                    response.getBody();

            String gatewayId = null;

            if(responseBody != null
                    && responseBody.get("key") instanceof Map<?, ?> keyMap) {

                Object id =
                        keyMap.get("id");

                if(id != null) {

                    gatewayId =
                            id.toString();
                }
            }

            return NotificacaoResponse.builder()
                    .gatewayId(gatewayId)
                    .telefone(
                            request.getTelefone()
                    )
                    .mensagem(
                            request.getMensagem()
                    )
                    .status(
                            StatusNotificacao.ENVIADO
                    )
                    .httpStatus(
                            response.getStatusCode().value()
                    )
                    .respostaGateway(
                            responseBody != null
                                    ? responseBody.toString()
                                    : null
                    )
                    .dataHoraEnvio(
                            agora
                    )
                    .build();

        } catch (HttpStatusCodeException e) {

            log.error(
                    "Erro WhatsApp Evolution: {}",
                    e.getResponseBodyAsString()
            );

            return NotificacaoResponse.builder()
                    .telefone(
                            request.getTelefone()
                    )
                    .mensagem(
                            request.getMensagem()
                    )
                    .status(
                            StatusNotificacao.FALHA
                    )
                    .httpStatus(
                            e.getStatusCode().value()
                    )
                    .erro(
                            e.getResponseBodyAsString()
                    )
                    .respostaGateway(
                            e.getResponseBodyAsString()
                    )
                    .dataHoraEnvio(
                            agora
                    )
                    .build();

        } catch (Exception e) {

            log.error(
                    "Erro ao enviar WhatsApp",
                    e
            );

            return NotificacaoResponse.builder()
                    .telefone(
                            request.getTelefone()
                    )
                    .mensagem(
                            request.getMensagem()
                    )
                    .status(
                            StatusNotificacao.FALHA
                    )
                    .httpStatus(
                            500
                    )
                    .erro(
                            e.getMessage()
                    )
                    .respostaGateway(
                            e.getMessage()
                    )
                    .dataHoraEnvio(
                            agora
                    )
                    .build();
        }
    }
}