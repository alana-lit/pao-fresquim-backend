package br.com.paofresquim.service.testeservice;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class TesteEnvioEmail {

    public static void main(String[] args) {

        try {

            String url =
                    "https://api.resend.com/emails";

            String apiKey =
                    "";

            RestTemplate restTemplate =
                    new RestTemplate();

            HttpHeaders headers =
                    new HttpHeaders();

            headers.setContentType(
                    MediaType.APPLICATION_JSON
            );

            headers.setBearerAuth(apiKey);

            Map<String, Object> body =
                    new HashMap<>();

            body.put(
                    "from",
                    "Pão Fresquim <onboarding@resend.dev>"
            );

            body.put(
                    "to",
                    "nff.allysson@gmail.com"
            );

            body.put(
                    "subject",
                    "Teste envio email"
            );

            body.put(
                    "html",
                    "<h1>Email funcionando</h1>"
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

