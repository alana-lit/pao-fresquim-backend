package br.com.paofresquim.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailRequest {

    private String destinatario;

    private String assunto;

    private String mensagem;

    private Boolean cobranca;
}