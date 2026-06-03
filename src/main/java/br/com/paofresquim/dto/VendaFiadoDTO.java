package br.com.paofresquim.dto;

import br.com.paofresquim.dto.request.ClienteRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class VendaFiadoDTO {

    private Integer idFuncionario;

    @NotNull(message = "Cliente é obrigatório")
    private ClienteRequest cliente;

    @NotEmpty(message = "A venda deve possuir ao menos um item")
    private List<ItensVendaDTO> itens;

}