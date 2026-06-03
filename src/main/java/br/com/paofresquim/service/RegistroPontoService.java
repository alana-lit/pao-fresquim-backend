package br.com.paofresquim.service;

import br.com.paofresquim.dto.request.RegistroPontoRequest;
import br.com.paofresquim.dto.response.RegistroPontoResponse;
import br.com.paofresquim.model.Funcionario;
import br.com.paofresquim.model.RegistroPonto;
import br.com.paofresquim.repository.FuncionarioRepository;
import br.com.paofresquim.repository.RegistroPontoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistroPontoService {

    private final RegistroPontoRepository registroPontoRepository;

    private final FuncionarioRepository funcionarioRepository;

    @Transactional(readOnly = true)
    public List<RegistroPontoResponse> listarPontos() {

        return registroPontoRepository.findAll().stream()
                .map(RegistroPontoResponse::new).toList();
    }

    @Transactional(readOnly = true)
    public List<RegistroPontoResponse> listarPontosEspecificos(Long funcionarioId) throws Exception {

        Funcionario funcionario = funcionarioRepository.findById(funcionarioId)
                .orElseThrow(() -> new Exception("Funcionário não encontrado"));
        return registroPontoRepository.findByFuncionario(funcionario).stream()
                .map(RegistroPontoResponse::new).toList();
    }

    @Transactional
    public RegistroPontoResponse registrarPonto(RegistroPontoRequest request) throws Exception {

        Funcionario funcionario = funcionarioRepository
                .findByMatricula(request.getMatricula().longValue()).orElseThrow(() -> new Exception("Funcionário não encontrado"));

        RegistroPonto registroPonto = new RegistroPonto();

        registroPonto.setFuncionario(funcionario);

        registroPonto.setDataRegistro(LocalDate.now());

        registroPonto.setHorario(LocalTime.now());

        registroPonto.setFalta(false);

        registroPonto.setFeriado(false);

        return new RegistroPontoResponse(registroPontoRepository.save(registroPonto));
    }

    @Transactional
    public RegistroPontoResponse atualizarPonto(Long funcionarioId, Long pontoId, RegistroPontoRequest request) throws Exception {

        Funcionario funcionario = funcionarioRepository.findById(funcionarioId).orElseThrow(() -> new Exception("Funcionário não encontrado"));

        RegistroPonto registroPonto = registroPontoRepository.findById(pontoId).orElseThrow(() -> new Exception("Registro de ponto não encontrado"));

        // valida se o ponto pertence ao funcionário
        if (!registroPonto.getFuncionario().getId().equals(funcionario.getId())) {

            throw new Exception("Este ponto não pertence ao funcionário");
        }

        registroPonto.setDataRegistro(request.getDataRegistro());

        registroPonto.setHorario(request.getHorario());

        return new RegistroPontoResponse(registroPontoRepository.save(registroPonto));
    }

    @Transactional
    public void excluirPonto(Long funcionarioId, Long pontoId) throws Exception {

        Funcionario funcionario = funcionarioRepository.findById(funcionarioId).orElseThrow(() -> new Exception("Funcionário não encontrado"));

        RegistroPonto registroPonto = registroPontoRepository.findById(pontoId).orElseThrow(() -> new Exception("Registro de ponto não encontrado"));

        // valida se o ponto pertence ao funcionário
        if (!registroPonto.getFuncionario().getId().equals(funcionario.getId())) {

            throw new Exception("Este ponto não pertence ao funcionário");
        }

        registroPontoRepository.delete(registroPonto);
    }

    @Transactional
    public void verificarFaltasEFeriados() {

        LocalDate hoje = LocalDate.now();
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        List<LocalDate> feriados = List.of(
                // Feriados Nacionais
                LocalDate.of(hoje.getYear(), 1, 1),   // Ano Novo / Confraternização Universal
                LocalDate.of(hoje.getYear(), 4, 21),  // Tiradentes
                LocalDate.of(hoje.getYear(), 5, 1),   // Dia do Trabalhador
                LocalDate.of(hoje.getYear(), 9, 7),   // Independência do Brasil
                LocalDate.of(hoje.getYear(), 10, 12), // Nossa Senhora Aparecida / Padroeira do Brasil
                LocalDate.of(hoje.getYear(), 11, 2),  // Finados
                LocalDate.of(hoje.getYear(), 11, 15), // Proclamação da República
                LocalDate.of(hoje.getYear(), 11, 20), // Dia Nacional de Zumbi e da Consciência Negra
                LocalDate.of(hoje.getYear(), 12, 25) // Natal
        );
        boolean ehFeriado = feriados.contains(hoje);
        for (Funcionario funcionario : funcionarios) {
            boolean possuiPonto = registroPontoRepository.existsByFuncionarioAndDataRegistro(funcionario, hoje);

            if (!possuiPonto) {
                RegistroPonto registro = new RegistroPonto();
                registro.setFuncionario(funcionario);
                registro.setDataRegistro(hoje);
                registro.setHorario(null);
                registro.setFalta(!ehFeriado);
                registro.setFeriado(ehFeriado);
                registroPontoRepository.save(registro);
            }
        }
    }

    @Scheduled(cron = "0 59 23 * * *")
    public void fechamentoDiario() {
        verificarFaltasEFeriados();
    }
}