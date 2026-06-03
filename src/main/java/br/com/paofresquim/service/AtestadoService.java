package br.com.paofresquim.service;

import br.com.paofresquim.dto.request.AtestadoRequest;
import br.com.paofresquim.dto.response.AtestadoResponse;
import br.com.paofresquim.model.Atestado;
import br.com.paofresquim.model.Funcionario;
import br.com.paofresquim.repository.AtestadoRepository;
import br.com.paofresquim.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AtestadoService {

    private final AtestadoRepository atestadoRepository;

    private final FuncionarioRepository funcionarioRepository;

    @Transactional
    public AtestadoResponse registrarAtestado(AtestadoRequest request) throws Exception {

        Funcionario funcionario = funcionarioRepository
                .findByMatricula(request.getMatricula().longValue())
                        .orElseThrow(() -> new Exception("Funcionário não encontrado"));

        Atestado atestado = new Atestado();
        preencherDados(atestado, request, funcionario);

        return new AtestadoResponse(
                atestadoRepository.save(atestado)
        );
    }
    @Transactional
    public AtestadoResponse atualizarAtestado(Long funcionarioId, Long pontoId, AtestadoRequest request) throws Exception {

        Funcionario funcionario = funcionarioRepository.findById(funcionarioId)
                .orElseThrow(() -> new Exception("Funcionário não encontrado"));

        Atestado atestado = atestadoRepository.findById(pontoId)
                .orElseThrow(() -> new Exception("Registro de ponto não encontrado"));

        // valida se o atestado pertence ao funcionário
        if (!atestado.getFuncionario().getId().equals(funcionario.getId())) {

            throw new Exception("Este atestado não pertence ao funcionário");
        }

        atestado.setDataInicio(request.getDataInicio());
        atestado.setDataFim(request.getDataFim());

        return new AtestadoResponse(atestadoRepository.save(atestado));
    }

    @Transactional(readOnly = true)
    public List<AtestadoResponse> listarAtestadoEspecifico(Long funcionarioId)
            throws Exception { Funcionario funcionario = funcionarioRepository.findById(funcionarioId)
                        .orElseThrow(() -> new Exception("Funcionário não encontrado"));

        return atestadoRepository
                .findByFuncionario(funcionario)
                .stream()
                .map(AtestadoResponse::new)
                .toList();
    }

    private void preencherDados(Atestado atestado, AtestadoRequest request, Funcionario funcionario
    ) {
        atestado.setFuncionario(funcionario);
        atestado.setNome(request.getNome());
        //Base64.getDecoder().decode(request.getArquivo());
        atestado.setArquivo(request.getArquivo());
        atestado.setDataInicio(request.getDataInicio());
        atestado.setDataFim(request.getDataFim());
    }
}