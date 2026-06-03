package br.com.paofresquim.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WhatsappRequest {

    private String telefone;

    private String mensagem;

    private Boolean cobranca;
}