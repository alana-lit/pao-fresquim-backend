package br.com.paofresquim.dto.response;

import br.com.paofresquim.model.RegistroPonto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class RegistroPontoResponse {

    private Integer id;
    private Integer matricula;
    private String nomeFuncionario;
    private LocalDate dataRegistro;
    private LocalTime horario;
    private boolean falta;
    private boolean feriado;

    public RegistroPontoResponse(RegistroPonto registroPonto) {

        this.id = registroPonto.getId();
        this.matricula = registroPonto.getFuncionario().getMatricula();
        this.nomeFuncionario = registroPonto.getFuncionario().getPessoa().getNome();
        this.dataRegistro = registroPonto.getDataRegistro();
        this.horario = registroPonto.getHorario();
        this.falta = registroPonto.getFalta();
        this.feriado = registroPonto.getFeriado();
    }
}