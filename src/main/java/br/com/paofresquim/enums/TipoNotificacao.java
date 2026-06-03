package br.com.paofresquim.enums;

import lombok.Getter;

@Getter
public enum TipoNotificacao {

    WHATSAPP("WhatsApp"),
    EMAIL("E-mail"),
    SMS("SMS");

    private final String descricao;

    TipoNotificacao(String descricao) {
        this.descricao = descricao;
    }
}
