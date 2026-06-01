package br.com.paofresquim.dto.response;

import br.com.paofresquim.model.Cliente;
import br.com.paofresquim.model.Pessoa;
import lombok.Getter;

import java.util.Date;

@Getter
public class ClienteResponse {

    private final Integer id;
    private final Pessoa pessoa;
    private final Boolean estadoSerasa;
    private final Boolean inativo;

    public ClienteResponse(Cliente cliente) {
        this.id = cliente.getId();
        this.pessoa = cliente.getPessoa();
        this.estadoSerasa = cliente.getEstadoSerasa();
        this.inativo = cliente.getInativo();
    }
}