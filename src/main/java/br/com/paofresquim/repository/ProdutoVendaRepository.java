package br.com.paofresquim.repository;

import br.com.paofresquim.model.ProdutoVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoVendaRepository extends JpaRepository<ProdutoVenda, Integer> {
}