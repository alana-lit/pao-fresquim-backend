package br.com.paofresquim.service;//package br.com.paofresquim.service;
//
//import br.com.paofresquim.dto.request.NfERequest;
//import br.com.paofresquim.dto.response.NfEResponse;
//import br.com.paofresquim.model.NfE;
//import br.com.paofresquim.model.Venda;
//import br.com.paofresquim.repository.NfERepository;
//import br.com.paofresquim.repository.VendaRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.List;
//import java.util.UUID;
//
//@Service
//@RequiredArgsConstructor
//public class NfEService {
//
//    private final NfERepository nfERepository;
//
//    private final VendaRepository vendaRepository;
//
//    @Transactional(readOnly = true)
//    public List<NfEResponse> listarNotas() {
//
//        return nfERepository.findAll()
//                .stream()
//                .map(NfEResponse::new)
//                .toList();
//    }
//
//    @Transactional(readOnly = true)
//    public NfEResponse buscarNotaPorId(Integer id) throws Exception {
//
//        NfE nfE = buscarNotaOuLancarExcecao(id);
//        return new NfEResponse(nfE);
//    }
//
//    @Transactional
//    public NfEResponse emitirNota(
//            NfERequest request
//    ) throws Exception {
//        Venda venda = vendaRepository.findById(request.getVendaId())
//                .orElseThrow(() -> new Exception("Venda não encontrada."));
//
//        if (nfERepository.findByVendaId(venda.getId()).isPresent()) {
//            throw new Exception("Já existe uma nota para esta venda.");
//        }
//
//        NfE nfE = new NfE();
//        nfE.setVenda(venda);
//
//        // Dados automáticos
//        nfE.setDataNf(LocalDate.now());
//        nfE.setHora(LocalTime.now());
//
//        // Dados fixos empresa
//        nfE.setEstado("MG");
//        nfE.setCnpj("12.345.678/0001-99");
//        nfE.setNomeFantasia("Pão Fresquim");
//        nfE.setNomeEmpresarial("Pão Fresquim LTDA");
//
//        // Valor da venda
//        nfE.setValor(venda.getValor());
//
//        // CPF opcional
//        nfE.setCpf(request.getCpf());
//
//        // Chave automática
//        String chave = UUID.randomUUID()
//                        .toString()
//                        .replace("-", "");
//        nfE.setChaveAcesso(chave.substring(0, 44));
//
//        return new NfEResponse(nfERepository.save(nfE));
//    }
//
//    @Transactional
//    public void deletarNota(
//            Integer id
//    ) throws Exception {
//
//        NfE nfE = buscarNotaOuLancarExcecao(id);
//        nfERepository.delete(nfE);
//    }
//
//    private NfE buscarNotaOuLancarExcecao(Integer id) throws Exception {
//
//        return nfERepository.findById(id)
//                .orElseThrow(() -> new Exception("Nota fiscal com ID " + id + " não encontrada."));
//    }
//}