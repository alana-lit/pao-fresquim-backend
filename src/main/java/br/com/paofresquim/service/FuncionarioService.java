package br.com.paofresquim.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import br.com.paofresquim.dto.request.FuncionarioRequest;
import br.com.paofresquim.dto.response.FuncionarioResponse;
import br.com.paofresquim.model.Funcionario;
import br.com.paofresquim.model.Pessoa;
import br.com.paofresquim.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    @Transactional(readOnly = true)
    public List<FuncionarioResponse> ListarFuncionarios() {
        return funcionarioRepository.findAll()
                .stream()
                .map(FuncionarioResponse::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public FuncionarioResponse buscarFuncionarioId (Long id) throws Exception {
        Funcionario funcionario = buscarFuncionarioOulancarExcecao(id);
        return new FuncionarioResponse(funcionario);
    }

    @Transactional(readOnly = true)
    public FuncionarioResponse buscarFuncionarioMatricula (long matricula) throws Exception {
        Funcionario funcionario = buscarFuncionarioMatriculaOulancarExcecao(matricula);
        return new FuncionarioResponse(funcionario);
    }

    @Transactional
    public FuncionarioResponse criarFuncionario (FuncionarioRequest request){
        Funcionario funcionario = new Funcionario();
        preencherDados(funcionario, request);
        return new FuncionarioResponse(funcionarioRepository.save(funcionario));
    }

    @Transactional
    public FuncionarioResponse atualizarFuncionario(Long id, FuncionarioRequest request) throws Exception {
        Funcionario funcionario = buscarFuncionarioOulancarExcecao(id);
        preencherDados(funcionario, request);
        return new FuncionarioResponse(funcionarioRepository.save(funcionario));
    }

    @Transactional
    public void deletarFuncionario(Long id) throws Exception {
        if (!funcionarioRepository.existsById(id)) {
            throw new Exception("Cliente com ID " + id + " não encontrado.");
        }
        funcionarioRepository.deleteById(id);
    }

    private Funcionario buscarFuncionarioOulancarExcecao(Long id) throws Exception {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new Exception("Cliente com ID " + id + " não encontrado."));
    }
    private Funcionario buscarFuncionarioMatriculaOulancarExcecao(long matricula) throws Exception {
        return funcionarioRepository.findByMatricula(matricula)
                .orElseThrow(() -> new Exception("Cliente com ID " + matricula + " não encontrado."));
    }


    private Funcionario calcularFerias(long id) throws Exception {

        Funcionario funcionario = buscarFuncionarioOulancarExcecao(id);

        LocalDate dataContratacao = funcionario.getDataContratacao();
        LocalDate dataAtual = LocalDate.now();
        long mesesTrabalhados = ChronoUnit.MONTHS.between(dataContratacao, dataAtual);
        long diasTrabalhados = ChronoUnit.DAYS.between(dataContratacao, dataAtual);

        if (mesesTrabalhados >= 23) {

            System.out.println("ALERTA CRÍTICO: Funcionário com " + mesesTrabalhados +
                    " meses de casa. As férias vão vencer! Prazo máximo estourado.");
        }

        else if (diasTrabalhados >= 365) {

            LocalDate limiteParaFerias = dataContratacao.plusMonths(23);

            System.out.println("Funcionário está no período de férias.");
            System.out.println("Ele tem até o mês de " + limiteParaFerias.getMonth() +
                    " de " + limiteParaFerias.getYear() + " para gozar as férias sem vencer.");
        }

        else {
            long diasRestantes = 365 - diasTrabalhados;
            System.out.println("Ainda não está no período aquisitivo. Faltam " + diasRestantes + " dias para completar 1 ano.");
        }

        return funcionario;
    }


    private void preencherDados (Funcionario funcionario, FuncionarioRequest request){
        if(funcionario.getPessoa() == null){
            funcionario.setPessoa(new Pessoa());
        }

        funcionario.getPessoa().setNome(request.getNome());
        funcionario.getPessoa().setCpf(request.getCpf());
        funcionario.getPessoa().setEmail(request.getEmail());
        funcionario.getPessoa().setTelefone(request.getTelefone());
        funcionario.setDataContratacao(request.getDataContratacao());
        funcionario.setSalario(request.getSalario());
        funcionario.setDataFimSalario(request.getDataFimSalario());
        funcionario.setFeriasInicio(request.getFeriasInicio());
        funcionario.setFeriasFim(request.getFeriasFim());
        funcionario.setPisPasep(request.getPisPasep());
        funcionario.setPisPasep(request.getPisPasep());
        funcionario.setCargo(request.getCargo());
        funcionario.setMatricula(request.getMatricula());
        funcionario.setContatoEmergencia(request.getContatoEmergencia());
        funcionario.setNomeContatoEmergencia(request.getNomeContatoEmergencia());
    }
}
