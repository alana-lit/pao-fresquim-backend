package br.com.paofresquim.repository;

import br.com.paofresquim.enums.StatusNotificacao;
import br.com.paofresquim.model.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificacaoRepository
        extends JpaRepository<Notificacao, Long> {

    Optional<Notificacao> findByGatewayId(
            String gatewayId
    );

    List<Notificacao> findByStatus(
            StatusNotificacao status
    );
}