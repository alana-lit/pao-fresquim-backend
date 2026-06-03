package br.com.paofresquim.enums;

import lombok.Getter;

@Getter
public enum MeioPagamento {

    DEBITO("Débito"),
    CREDITO("Crédito"),
    PIX("Pix");

    private final String descricao;

    MeioPagamento(String descricao) {
        this.descricao = descricao;
    }
}