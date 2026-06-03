package br.com.paofresquim.controller;

import br.com.paofresquim.dto.request.WhatsappWebhookRequest;
import br.com.paofresquim.enums.StatusNotificacao;
import br.com.paofresquim.model.Notificacao;
import br.com.paofresquim.repository.NotificacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhook/whatsapp")
@RequiredArgsConstructor
public class WhatsappWebhookController {

    private final NotificacaoRepository notificacaoRepository;

    @PostMapping
    public void atualizarStatus(
            @RequestBody WhatsappWebhookRequest request
    ) {

        Notificacao notificacao =
                notificacaoRepository
                        .findByGatewayId(
                                request.getMessageId()
                        )
                        .orElse(null);

        if(notificacao == null) {
            return;
        }

        notificacao.setRespostaGateway(
                request.getResponse()
        );

        notificacao.setHttpStatus(
                request.getHttpStatus()
        );

        if(request.getStatus() != null) {

            switch (
                    request.getStatus().toUpperCase()
            ) {

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

        notificacaoRepository.save(
                notificacao
        );
    }
}