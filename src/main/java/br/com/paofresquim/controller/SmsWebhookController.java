package br.com.paofresquim.controller;

import br.com.paofresquim.dto.request.SmsWebhookRequest;
import br.com.paofresquim.enums.StatusNotificacao;
import br.com.paofresquim.model.Notificacao;
import br.com.paofresquim.repository.NotificacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhook/sms")
@RequiredArgsConstructor
public class SmsWebhookController {

    private final NotificacaoRepository
            notificacaoRepository;

    @PostMapping
    public void atualizarStatus(
            @RequestBody SmsWebhookRequest request
    ) {

        Notificacao notificacao =
                notificacaoRepository
                        .findByGatewayId(
                                request.getSms_id()
                        )
                        .orElse(null);

        if (notificacao == null) {
            return;
        }

        String status =
                request.getStatus_message();

        if (status == null) {

            notificacao.setStatus(
                    StatusNotificacao.ERRO
            );

        } else {

            switch (status.toUpperCase()) {

                case "DELIVERED":
                case "SENT":
                case "SUCCESS":

                    notificacao.setStatus(
                            StatusNotificacao.ENVIADO
                    );
                    break;

                case "FAILED":
                case "ERROR":

                    notificacao.setStatus(
                            StatusNotificacao.FALHA
                    );
                    break;

                default:

                    notificacao.setStatus(
                            StatusNotificacao.ERRO
                    );
            }
        }

        notificacao.setRespostaGateway(
                request.getResponse()
        );

        notificacao.setHttpStatus(
                request.getHttp_status()
        );

        notificacaoRepository.save(
                notificacao
        );
    }
}