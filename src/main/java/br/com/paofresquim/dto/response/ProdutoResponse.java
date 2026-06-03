package br.com.paofresquim.dto.response;

import br.com.paofresquim.model.Produto;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
public class ProdutoResponse {

    private Integer id;
    private Integer codigo;
    private String nomeProduto;
    private Date dataFabricacao;
    private Date dataValidade;
    private String lote;
    private String codigoBarra;
    private String categoria;
    private String alergicos;
    private String descricao;
    private BigDecimal precoUnitario;

    public ProdutoResponse(Produto produto) {
        this.id = produto.getId();
        this.codigo = produto.getCodigo();
        this.nomeProduto = produto.getNomeProduto();
        this.dataFabricacao = produto.getDataFabricacao();
        this.dataValidade = produto.getDataValidade();
        this.lote = produto.getLote();
        this.codigoBarra = produto.getCodigoBarra();
        this.categoria = produto.getCategoria();
        this.alergicos = produto.getAlergicos();
        this.descricao = produto.getDescricao();
        this.precoUnitario = produto.getPrecoUnitario();
    }
}
