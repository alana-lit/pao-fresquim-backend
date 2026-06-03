package br.com.paofresquim.service;//package br.com.paofresquim.service;
//
//import br.com.paofresquim.dto.request.EmailRequest;
//import br.com.paofresquim.dto.request.NotificacaoRequest;
//import br.com.paofresquim.dto.request.WhatsappRequest;
//import br.com.paofresquim.dto.response.NotificacaoResponse;
//import br.com.paofresquim.enums.StatusNotificacao;
//import br.com.paofresquim.enums.StatusPagamento;
//import br.com.paofresquim.enums.TipoNotificacao;
//import br.com.paofresquim.integration.EmailIntegration;
//import br.com.paofresquim.integration.SmsIntegration;
//import br.com.paofresquim.integration.WhatsappIntegration;
//import br.com.paofresquim.model.Cliente;
//import br.com.paofresquim.model.Notificacao;
//import br.com.paofresquim.model.Venda;
//import br.com.paofresquim.repository.NotificacaoRepository;
//import br.com.paofresquim.repository.VendaRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.time.temporal.ChronoUnit;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class NotificacaoService {
//
//    private final VendaRepository vendaRepository;
//    private final NotificacaoRepository notificacaoRepository;
//
//    private final SmsIntegration smsIntegration;
//    private final EmailIntegration emailIntegration;
//    private final WhatsappIntegration whatsappIntegration;
//
//    @Scheduled(cron = "0 0 7 * * *")
//    public void executarRotina() {
//
//        enviarCobrancas();
//
//        enviarSerasa();
//
//        reenviarFalhas();
//    }
//
//    public void enviarCobrancas() {
//
//        List<Venda> vendas =
//                vendaRepository.findByStatusPagamento(
//                        StatusPagamento.PENDENTE
//                );
//
//        for (Venda venda : vendas) {
//
//            long dias =
//                    ChronoUnit.DAYS.between(
//                            venda.getDataVenda(),
//                            LocalDate.now()
//                    );
//
//            if (dias >= 20 && dias <= 30) {
//
//                Cliente cliente =
//                        venda.getCliente();
//
//                String mensagem =
//                        montarMensagemCobranca(
//                                cliente,
//                                venda,
//                                dias
//                        );
//
//                enviarMulticanal(
//                        venda,
//                        cliente,
//                        mensagem,
//                        true
//                );
//            }
//        }
//    }
//
//    public void enviarSerasa() {
//
//        List<Venda> vendas =
//                vendaRepository.findByStatusPagamento(
//                        StatusPagamento.PENDENTE
//                );
//
//        for (Venda venda : vendas) {
//
//            long dias =
//                    ChronoUnit.DAYS.between(
//                            venda.getDataVenda(),
//                            LocalDate.now()
//                    );
//
//            if (dias <= 30) {
//                continue;
//            }
//
//            Cliente cliente =
//                    venda.getCliente();
//
//            BigDecimal valorAberto =
//                    venda.getValorTotal()
//                            .subtract(venda.getValorPago());
//
//            String mensagem;
//
//            if (dias <= 40) {
//
//                mensagem =
//                        "ATENÇÃO " + cliente.getNome()
//                                + ", identificamos atraso referente à compra realizada em "
//                                + venda.getDataVenda()
//                                + ". Valor em aberto: R$ "
//                                + valorAberto
//                                + ". Caso não haja regularização, seu nome poderá ser negativado junto ao SERASA.";
//
//            } else {
//
//                mensagem =
//                        "ATENÇÃO " + cliente.getNome()
//                                + ", informamos que seu nome foi negativado junto ao SERASA referente à compra realizada em "
//                                + venda.getDataVenda()
//                                + ". Valor em aberto: R$ "
//                                + valorAberto
//                                + ". Entre em contato para regularização.";
//            }
//
//            enviarMulticanal(
//                    venda,
//                    cliente,
//                    mensagem,
//                    false
//            );
//        }
//    }
//
//    private String montarMensagemCobranca(
//            Cliente cliente,
//            Venda venda,
//            long dias
//    ) {
//
//        BigDecimal valorAberto =
//                venda.getValorTotal()
//                        .subtract(venda.getValorPago());
//
//        long diasRestantes =
//                30 - dias;
//
//        if (dias == 30) {
//
//            return "Olá "
//                    + cliente.getNome()
//                    + ", sua cobrança vence hoje. Compra realizada em "
//                    + venda.getDataVenda()
//                    + ". Valor em aberto: R$ "
//                    + valorAberto
//                    + ".";
//        }
//
//        return "Olá "
//                + cliente.getNome()
//                + ", identificamos uma cobrança referente à compra realizada em "
//                + venda.getDataVenda()
//                + ". Valor em aberto: R$ "
//                + valorAberto
//                + ". Faltam "
//                + diasRestantes
//                + " dia(s) para o vencimento.";
//    }
//
//    private void enviarMulticanal(
//            Venda venda,
//            Cliente cliente,
//            String mensagem,
//            boolean cobranca
//    ) {
//
//        enviarSms(
//                venda,
//                cliente,
//                mensagem,
//                cobranca
//        );
//
//        enviarWhatsapp(
//                venda,
//                cliente,
//                mensagem,
//                cobranca
//        );
//
//        enviarEmail(
//                venda,
//                cliente,
//                mensagem,
//                cobranca
//        );
//    }
//
//    private void enviarSms(
//            Venda venda,
//            Cliente cliente,
//            String mensagem,
//            boolean cobranca
//    ) {
//
//        try {
//
//            NotificacaoResponse response =
//                    smsIntegration.enviar(
//                            NotificacaoRequest.builder()
//                                    .telefone(cliente.getTelefone())
//                                    .mensagem(mensagem)
//                                    .cobranca(cobranca)
//                                    .build()
//                    );
//
//            salvar(
//                    venda,
//                    cliente,
//                    mensagem,
//                    TipoNotificacao.SMS,
//                    response,
//                    cobranca
//            );
//
//        } catch (Exception e) {
//
//            log.error("Erro SMS", e);
//        }
//    }
//
//    private void enviarWhatsapp(
//            Venda venda,
//            Cliente cliente,
//            String mensagem,
//            boolean cobranca
//    ) {
//
//        try {
//
//            NotificacaoResponse response =
//                    whatsappIntegration.enviar(
//                            WhatsappRequest.builder()
//                                    .telefone(cliente.getTelefone())
//                                    .mensagem(mensagem)
//                                    .cobranca(cobranca)
//                                    .build()
//                    );
//
//            salvar(
//                    venda,
//                    cliente,
//                    mensagem,
//                    TipoNotificacao.WHATSAPP,
//                    response,
//                    cobranca
//            );
//
//        } catch (Exception e) {
//
//            log.error("Erro WhatsApp", e);
//        }
//    }
//
//    private void enviarEmail(
//            Venda venda,
//            Cliente cliente,
//            String mensagem,
//            boolean cobranca
//    ) {
//
//        try {
//
//            String html =
//                    montarHtml(
//                            cliente,
//                            mensagem
//                    );
//
//            NotificacaoResponse response =
//                    emailIntegration.enviar(
//                            EmailRequest.builder()
//                                    .destinatario(cliente.getEmail())
//                                    .assunto("Pão Fresquim")
//                                    .mensagem(html)
//                                    .cobranca(cobranca)
//                                    .build()
//                    );
//
//            salvar(
//                    venda,
//                    cliente,
//                    mensagem,
//                    TipoNotificacao.EMAIL,
//                    response,
//                    cobranca
//            );
//
//        } catch (Exception e) {
//
//            log.error("Erro Email", e);
//        }
//    }
//
//    private String montarHtml(
//            Cliente cliente,
//            String mensagem
//    ) {
//
//        return """
//                <html>
//                <body style="font-family: Arial">
//                    <h2>Olá %s</h2>
//                    <p>%s</p>
//                    <hr>
//                    <p>Pão Fresquim</p>
//                </body>
//                </html>
//                """.formatted(
//                cliente.getNome(),
//                mensagem
//        );
//    }
//
//    private void salvar(
//            Venda venda,
//            Cliente cliente,
//            String mensagem,
//            TipoNotificacao tipo,
//            NotificacaoResponse response,
//            boolean cobranca
//    ) {
//
//        Notificacao notificacao =
//                Notificacao.builder()
//                        .cliente(cliente)
//                        .venda(venda)
//                        .tipo(tipo)
//                        .mensagem(mensagem)
//                        .status(response.getStatus())
//                        .gatewayId(response.getGatewayId())
//                        .httpStatus(response.getHttpStatus())
//                        .erro(response.getErro())
//                        .respostaGateway(response.getRespostaGateway())
//                        .dataEnvio(response.getDataHoraEnvio())
//                        .cobranca(cobranca)
//                        .build();
//
//        notificacaoRepository.save(
//                notificacao
//        );
//    }
//
//    public void reenviarFalhas() {
//
//        List<Notificacao> falhas =
//                notificacaoRepository.findByStatus(
//                        StatusNotificacao.FALHA
//                );
//
//        for (Notificacao notificacao : falhas) {
//
//            enviarMulticanal(
//                    notificacao.getVenda(),
//                    notificacao.getCliente(),
//                    notificacao.getMensagem(),
//                    notificacao.isCobranca()
//            );
//        }
//    }
//}