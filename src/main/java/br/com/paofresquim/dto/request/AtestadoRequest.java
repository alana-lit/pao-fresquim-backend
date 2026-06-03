package br.com.paofresquim.dto.request;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Data
public class AtestadoRequest {

    private Integer matricula;
    private String arquivo;
    private String nome;
    private LocalDate dataInicio;
    private LocalDate dataFim;
}
