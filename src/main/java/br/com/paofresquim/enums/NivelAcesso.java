package br.com.paofresquim.enums;

import lombok.Getter;

@Getter
public enum NivelAcesso {

    ADMIN("Administrador"),
    FUNCIONARIO("Funcionário");

    private final String descricao;

    NivelAcesso(String descricao) {
        this.descricao = descricao;
    }
}
