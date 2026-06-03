package br.com.paofresquim.dto.request;

import lombok.Data;

@Data
public class WhatsappWebhookRequest {

    private String messageId;

    private String status;

    private String response;

    private Integer httpStatus;
}