package br.com.paofresquim.controller;

import br.com.paofresquim.dto.request.AtestadoRequest;
import br.com.paofresquim.dto.response.AtestadoResponse;
import br.com.paofresquim.service.AtestadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/atestado")
@RequiredArgsConstructor
public class AtestadoController {

    private final AtestadoService atestadoService;


    @GetMapping("/{funcionarioId}")
    public ResponseEntity<List<AtestadoResponse>> listarAtestadosEspecificos(@PathVariable Long funcionarioId) throws Exception {
        List<AtestadoResponse> atestado = atestadoService.listarAtestadoEspecifico(funcionarioId);
        return ResponseEntity.ok(atestado);
    }

    @PostMapping
    public ResponseEntity<AtestadoResponse> resgistrarAtestado(@RequestBody AtestadoRequest request) throws Exception {
        AtestadoResponse response = atestadoService.registrarAtestado(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity
                .created(location)
                .body(response);
    }

    @PutMapping("/{funcionarioId}/{atestadoId}")
    public ResponseEntity<AtestadoResponse> atualizarAtestado(
            @PathVariable Long funcionarioId,
            @PathVariable Long atestadoId,
            @RequestBody AtestadoRequest request) throws Exception {

        AtestadoResponse response = atestadoService.atualizarAtestado(funcionarioId, atestadoId, request);
        return ResponseEntity.ok(response);
    }

}
