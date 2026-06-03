//package br.com.paofresquim.controller;
//
//import br.com.paofresquim.dto.request.NfERequest;
//import br.com.paofresquim.dto.response.NfEResponse;
//import br.com.paofresquim.service.NfEService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//import java.net.URI;
//import java.util.List;
//
//@RestController
//@RequestMapping("/nfe")
//@RequiredArgsConstructor
//public class NfEController {
//
//    private final NfEService nfEService;
//
//    @GetMapping
//    public ResponseEntity<List<NfEResponse>> listarNotas() {
//        return ResponseEntity.ok(nfEService.listarNotas());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<NfEResponse> buscarPorId(@PathVariable Integer id) throws Exception {
//
//        return ResponseEntity.ok(nfEService.buscarNotaPorId(id));
//    }
//
//    @PostMapping
//    public ResponseEntity<NfEResponse>
//    emitirNota(
//            @RequestBody
//            NfERequest request
//    ) throws Exception {
//
//        NfEResponse response =
//                nfEService.emitirNota(request);
//
//        URI location =
//                ServletUriComponentsBuilder
//                        .fromCurrentRequest()
//                        .path("/{id}")
//                        .buildAndExpand(
//                                response.getId()
//                        )
//                        .toUri();
//
//        return ResponseEntity
//                .created(location)
//                .body(response);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void>
//    deletarNota(
//            @PathVariable Integer id
//    ) throws Exception {
//
//        nfEService.deletarNota(id);
//
//        return ResponseEntity
//                .noContent()
//                .build();
//    }
//}