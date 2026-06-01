package br.com.paofresquim.controller;

import br.com.paofresquim.dto.request.RegistroPontoRequest;
import br.com.paofresquim.dto.response.RegistroPontoResponse;
import br.com.paofresquim.service.RegistroPontoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/registroPonto")
@RequiredArgsConstructor
public class RegistroPontoController {

    private final RegistroPontoService registroPontoService;

    @GetMapping
    public ResponseEntity<List<RegistroPontoResponse>> listarPontos() {
        List<RegistroPontoResponse> registroPonto = registroPontoService.listarPontos();
        return ResponseEntity.ok(registroPonto);
    }

    @GetMapping("/{funcionarioId}")
    public ResponseEntity<List<RegistroPontoResponse>> listarPontosEspecificos(@PathVariable Long funcionarioId) throws Exception {
        List<RegistroPontoResponse> registroPonto = registroPontoService.listarPontosEspecificos(funcionarioId);
        return ResponseEntity.ok(registroPonto);
    }

    @PostMapping
    public ResponseEntity<RegistroPontoResponse> baterPonto(@RequestBody RegistroPontoRequest request) throws Exception {
        RegistroPontoResponse response = registroPontoService.registrarPonto(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity
                .created(location)
                .body(response);
    }

    @PutMapping("/{funcionarioId}/{pontoId}")
    public ResponseEntity<RegistroPontoResponse> atualizarPonto(
            @PathVariable Long funcionarioId,
            @PathVariable Long pontoId,
            @RequestBody RegistroPontoRequest request) throws Exception {

        RegistroPontoResponse response = registroPontoService.atualizarPonto(funcionarioId, pontoId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{funcionarioId}/{pontoId}")
    public ResponseEntity<Void> excluirPonto(@PathVariable Long funcionarioId,
                                             @PathVariable Long pontoId) throws Exception {

        registroPontoService.excluirPonto(funcionarioId, pontoId);
        return ResponseEntity.noContent().build();
    }

}
