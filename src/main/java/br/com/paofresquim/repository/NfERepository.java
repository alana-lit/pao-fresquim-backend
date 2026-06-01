package br.com.paofresquim.repository;

import br.com.paofresquim.model.NfE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NfERepository
        extends JpaRepository<NfE, Integer> {

    Optional<NfE> findByChaveAcesso(String chaveAcesso);
    Optional<NfE> findByVendaId(Integer vendaId);
}