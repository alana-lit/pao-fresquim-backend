package br.com.paofresquim.dto.request;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Data
public class RegistroPontoRequest {

    private Integer matricula;
    private LocalDate dataRegistro;
    private LocalTime horario;
}