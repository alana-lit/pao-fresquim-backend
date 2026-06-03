package br.com.paofresquim.repository;

import br.com.paofresquim.model.Pagamento;
import br.com.paofresquim.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
}