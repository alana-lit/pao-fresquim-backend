package br.com.paofresquim.controller;

import br.com.paofresquim.dto.request.ClienteRequest;
import br.com.paofresquim.dto.request.FuncionarioRequest;
import br.com.paofresquim.dto.response.ClienteResponse;
import br.com.paofresquim.dto.response.FuncionarioResponse;
import br.com.paofresquim.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/funcionario")
@RequiredArgsConstructor
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @GetMapping
    public ResponseEntity<List<FuncionarioResponse>> listarFuncionarios() {
       List<FuncionarioResponse> funcionarios = funcionarioService.ListarFuncionarios();
       return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<FuncionarioResponse> buscarFuncionarioId(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(funcionarioService.buscarFuncionarioId(id));
    }

    @GetMapping("/id/{matricula}")
    public ResponseEntity<FuncionarioResponse> buscarFuncionarioMatricula(@PathVariable long matricula) throws Exception {
        return ResponseEntity.ok(funcionarioService.buscarFuncionarioMatricula(matricula));
    }

    @PostMapping
    public ResponseEntity<FuncionarioResponse> criar(@RequestBody FuncionarioRequest request) {
        FuncionarioResponse response = funcionarioService.criarFuncionario(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioResponse> atualizar(
            @PathVariable Long id,
            @RequestBody FuncionarioRequest request) throws Exception {
        return ResponseEntity.ok(funcionarioService.atualizarFuncionario(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) throws Exception {
        funcionarioService.deletarFuncionario(id);
        return ResponseEntity.noContent().build();
    }

}
