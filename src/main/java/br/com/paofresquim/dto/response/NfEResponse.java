package br.com.paofresquim.dto.response;

import br.com.paofresquim.model.NfE;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class NfEResponse {
    private Integer id;
    private Integer vendaId;
    private String estado;
    private LocalDate dataNf;
    private LocalTime hora;
    private String cnpj;
    private String nomeFantasia;
    private String nomeEmpresarial;
    private BigDecimal valor;
    private String cpf;
    private String chaveAcesso;
    public NfEResponse(NfE nfE) {

        this.id = nfE.getId();
        this.vendaId = nfE.getVenda().getId();
        this.estado = nfE.getEstado();
        this.dataNf = nfE.getDataNf();
        this.hora = nfE.getHora();
        this.cnpj = nfE.getCnpj();
        this.nomeFantasia = nfE.getNomeFantasia();
        this.nomeEmpresarial = nfE.getNomeEmpresarial();
        this.valor = nfE.getValor();
        this.cpf = nfE.getCpf();
        this.chaveAcesso = nfE.getChaveAcesso();
    }
}