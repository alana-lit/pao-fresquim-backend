package br.com.paofresquim.service;

import br.com.paofresquim.model.Produto;
import br.com.paofresquim.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public Produto criar(Produto produto) {
        if (produto.getCodigoBarra() != null &&
                produtoRepository.findByCodigoBarra(produto.getCodigoBarra()).isPresent()) {
            throw new RuntimeException("Já existe um produto com esse código de barras.");
        }
        if (produto.getCodigo() != null &&
                produtoRepository.findByCodigo(produto.getCodigo()).isPresent()) {
            throw new RuntimeException("Já existe um produto com esse código.");
        }
        return produtoRepository.save(produto);
    }

    @Transactional(readOnly = true)
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Produto> buscarPorId(Integer id) {
        return produtoRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Produto> buscarPorCodigo(Integer codigo) {
        return produtoRepository.findByCodigo(codigo);
    }

    @Transactional(readOnly = true)
    public Optional<Produto> buscarPorCodigoBarra(String codigoBarra) {
        return produtoRepository.findByCodigoBarra(codigoBarra);
    }

    @Transactional(readOnly = true)
    public Optional<Produto> buscarPorLote(String lote) {
        return produtoRepository.findByLote(lote);
    }

    @Transactional(readOnly = true)
    public List<Produto> buscarPorCategoria(String categoria) {
        return produtoRepository.findAll()
                .stream()
                .filter(p -> categoria.equalsIgnoreCase(p.getCategoria()))
                .toList();
    }

    @Transactional
    public Produto atualizar(Integer id, Produto produtoAtualizado) {
        Produto existente = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com id: " + id));

        existente.setCodigo(produtoAtualizado.getCodigo());
        existente.setNomeProduto(produtoAtualizado.getNomeProduto());
        existente.setDataFabricacao(produtoAtualizado.getDataFabricacao());
        existente.setDataValidade(produtoAtualizado.getDataValidade());
        existente.setLote(produtoAtualizado.getLote());
        existente.setCodigoBarra(produtoAtualizado.getCodigoBarra());
        existente.setCategoria(produtoAtualizado.getCategoria());
        existente.setAlergicos(produtoAtualizado.getAlergicos());
        existente.setDescricao(produtoAtualizado.getDescricao());
        existente.setPrecoUnitario(produtoAtualizado.getPrecoUnitario());

        return produtoRepository.save(existente);
    }

    @Transactional
    public void deletar(Integer id) {
        Produto existente = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com id: " + id));
        produtoRepository.delete(existente);
    }
}