package br.com.paofresquim.dto.response;
import br.com.paofresquim.model.Funcionario;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
@Getter
public class FuncionarioResponse {
    private final Integer id;
    private final String nome;
    private final String cpf;
    private final String email;
    private final String telefone;
    private final LocalDate dataContratacao;
    private final BigDecimal salario;
    private final LocalDate dataFimSalario;
    private final LocalDate feriasInicio;
    private final LocalDate feriasFim;
    private final String pisPasep;
    private final String cargo;
    private final Integer matricula;
    private final String contatoEmergencia;
    private final String nomeContatoEmergencia;

    public FuncionarioResponse(Funcionario funcionario) {
        this.id = funcionario.getId();
        this.nome = funcionario.getPessoa().getNome();
        this.cpf = funcionario.getPessoa().getCpf();
        this.email = funcionario.getPessoa().getEmail();
        this.telefone = funcionario.getPessoa().getTelefone();
        this.dataContratacao = funcionario.getDataContratacao();
        this.salario = funcionario.getSalario();
        this.dataFimSalario = funcionario.getDataFimSalario();
        this.feriasInicio = funcionario.getFeriasInicio();
        this.feriasFim = funcionario.getFeriasFim();
        this.pisPasep = funcionario.getPisPasep();
        this.cargo = funcionario.getCargo();
        this.matricula = funcionario.getMatricula();
        this.contatoEmergencia = funcionario.getContatoEmergencia();
        this.nomeContatoEmergencia = funcionario.getNomeContatoEmergencia();

    }

}
