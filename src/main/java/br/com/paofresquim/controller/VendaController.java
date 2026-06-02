package br.com.paofresquim.controller;

import br.com.paofresquim.dto.VendaFiadoDTO;
import br.com.paofresquim.dto.VendaVistaDTO;
import br.com.paofresquim.model.Venda;
import br.com.paofresquim.service.VendaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vendas")
@RequiredArgsConstructor
public class VendaController {

    private final VendaService vendaService;

    @PostMapping("/avista")
    public ResponseEntity<Venda> vendaVista(
            @RequestBody VendaVistaDTO dto) {

        return ResponseEntity.ok(
                vendaService.realizarVendaVista(dto)
        );
    }

    @PostMapping("/fiado")
    public ResponseEntity<?> vendaFiado(@Valid @RequestBody VendaFiadoDTO dto) {

        return ResponseEntity.ok(
                vendaService.realizarVendaFiada(dto)
        );
    }

}