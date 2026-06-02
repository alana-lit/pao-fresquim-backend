package br.com.paofresquim.dto;

import lombok.Data;

import java.util.List;

@Data
public class VendaVistaDTO {

    private Integer idFuncionario;

    private String meioPagamento;

    private String statusPagamento;

    private List<ItensVendaDTO> itens;

}