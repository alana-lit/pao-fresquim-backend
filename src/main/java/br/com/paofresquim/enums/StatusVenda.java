package br.com.paofresquim.enums;

import lombok.Getter;

@Getter
public enum StatusVenda {

    PAGA("Paga"),
    PENDENTE("Pendente");

    private final String descricao;

    StatusVenda(String descricao) {
        this.descricao = descricao;
    }
}
