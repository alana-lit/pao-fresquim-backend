package br.com.paofresquim.enums;

import lombok.Getter;

@Getter
public enum EstadoCivil {

    SOLTEIRO("Solteiro"),
    CASADO("Casado"),
    DIVORCIADO("Divorciado"),
    VIUVO("Viúvo");

    private final String descricao;

    EstadoCivil(String descricao) {
        this.descricao = descricao;
    }
}