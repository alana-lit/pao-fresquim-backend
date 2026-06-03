package br.com.paofresquim.dto.request;

import lombok.Data;

@Data
public class SmsWebhookRequest {

    private String sms_id;

    private String status_message;

    private String response;

    private Integer http_status;
}