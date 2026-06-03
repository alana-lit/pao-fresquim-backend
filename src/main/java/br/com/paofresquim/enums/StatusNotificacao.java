package br.com.paofresquim.enums;

import lombok.Getter;

@Getter
public enum StatusNotificacao {

    PENDENTE("Pendente"),
    ENVIADA("Enviada"),
    ERRO("Erro");

    private final String descricao;

    StatusNotificacao(String descricao) {
        this.descricao = descricao;
    }
}
