package br.com.paofresquim.dto.response;
import br.com.paofresquim.model.Atestado;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Data
public class AtestadoResponse {

    private final Integer id;
    private final Integer matricula;
    private final String arquivo;
    private final String nome;
    private final LocalDate dataInicio;
    private final LocalDate dataFim;

    public AtestadoResponse(Atestado atestado){

        this.id = atestado.getId();
        this.matricula = atestado.getFuncionario().getMatricula();
        this.arquivo = atestado.getArquivo();
        this.nome = atestado.getNome();
        this.dataInicio = atestado.getDataInicio();
        this.dataFim = atestado.getDataFim();
    }


}
