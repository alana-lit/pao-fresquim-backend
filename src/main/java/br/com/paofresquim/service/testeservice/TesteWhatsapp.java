package br.com.paofresquim.service.testeservice;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class TesteWhatsapp {

    public static void main(String[] args) {

        try {

            String url =
                    "http://localhost:5678/webhook/whatsapp";

            RestTemplate restTemplate =
                    new RestTemplate();

            HttpHeaders headers =
                    new HttpHeaders();

            headers.setContentType(
                    MediaType.APPLICATION_JSON
            );

            Map<String, Object> body =
                    new HashMap<>();

            body.put(
                    "telefone",
                    "+553499999999"
            );

            body.put(
                    "mensagem",
                    "Teste WhatsApp funcionando"
            );

            HttpEntity<Map<String, Object>> entity =
                    new HttpEntity<>(
                            body,
                            headers
                    );

            ResponseEntity<String> response =
                    restTemplate.exchange(
                            url,
                            HttpMethod.POST,
                            entity,
                            String.class
                    );

            System.out.println(
                    "STATUS: "
                            + response.getStatusCode()
            );

            System.out.println(
                    "BODY: "
                            + response.getBody()
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
