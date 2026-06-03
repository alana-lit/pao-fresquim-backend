package br.com.paofresquim.integration;

import br.com.paofresquim.dto.request.EmailRequest;
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
public class EmailIntegration {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${email.api.url}")
    private String url;

    @Value("${email.api.key}")
    private String apiKey;

    @Value("${email.from}")
    private String from;

    public NotificacaoResponse enviar(EmailRequest request) {

        LocalDateTime agora = LocalDateTime.now();

        try {

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(apiKey);

            Map<String, Object> body = new HashMap<>();
            body.put("from", from);
            body.put("to", request.getDestinatario());
            body.put("subject", request.getAssunto());
            body.put("html", "<h3>" + request.getMensagem() + "</h3>");

            HttpEntity<Map<String, Object>> entity =
                    new HttpEntity<>(body, headers);

            ResponseEntity<Map> response =
                    restTemplate.exchange(
                            url,
                            HttpMethod.POST,
                            entity,
                            Map.class
                    );

            Map responseBody = response.getBody();

            String gatewayId = null;

            if (responseBody != null && responseBody.get("id") != null) {
                gatewayId = responseBody.get("id").toString();
            }

            return NotificacaoResponse.builder()
                    .gatewayId(gatewayId)
                    .status(StatusNotificacao.ENVIADO)
                    .httpStatus(response.getStatusCode().value())
                    .respostaGateway(
                            responseBody != null ? responseBody.toString() : null
                    )
                    .dataHoraEnvio(agora)
                    .build();

        } catch (HttpStatusCodeException e) {

            log.error("Erro HTTP ao enviar e-mail: {}", e.getResponseBodyAsString(), e);

            return NotificacaoResponse.builder()
                    .status(StatusNotificacao.ERRO)
                    .httpStatus(e.getStatusCode().value())
                    .erro(e.getMessage())
                    .respostaGateway(e.getResponseBodyAsString())
                    .dataHoraEnvio(agora)
                    .build();

        } catch (Exception e) {

            log.error("Erro inesperado ao enviar e-mail", e);

            return NotificacaoResponse.builder()
                    .status(StatusNotificacao.FALHA)
                    .httpStatus(500)
                    .erro(e.getMessage())
                    .respostaGateway(e.getMessage())
                    .dataHoraEnvio(agora)
                    .build();
        }
    }
}