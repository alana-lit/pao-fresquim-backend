package br.com.paofresquim.dto.request;

import java.math.BigDecimal;
import java.util.Date;

public class ProdutoRequest {

    private String nomeProduto;
    private Date dataFabricacao;
    private Date dataValidade;
    private String lote;
    private String codigoBarra;
    private String categoria;
    private String alergicos;
    private String descricao;
    private BigDecimal precoUnitario;
}
