package br.com.paofresquim.repository;

import br.com.paofresquim.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Override
    Optional<Produto> findById(Integer id);
    Optional<Produto> findByCodigo(Integer codigo);
    Optional<Produto> findByLote(String lote);
    Optional<Produto> findByCodigoBarra(String codigoBarra);
}
