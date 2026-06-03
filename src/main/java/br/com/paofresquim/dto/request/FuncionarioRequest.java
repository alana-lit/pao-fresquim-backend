package br.com.paofresquim.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class FuncionarioRequest {

    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private LocalDate dataContratacao;
    private BigDecimal salario;
    private LocalDate dataFimSalario;
    private LocalDate feriasInicio;
    private LocalDate feriasFim;
    private String pisPasep;
    private String cargo;
    private Integer matricula;
    private String contatoEmergencia;
    private String nomeContatoEmergencia;
}
