package br.com.paofresquim.service;

import br.com.paofresquim.dto.ItensVendaDTO;
import br.com.paofresquim.dto.VendaVistaDTO;
import br.com.paofresquim.dto.VendaFiadoDTO;
import br.com.paofresquim.enums.MeioPagamento;
import br.com.paofresquim.enums.StatusVenda;
import br.com.paofresquim.exception.ClienteNegativadoException;
import br.com.paofresquim.model.*;
import br.com.paofresquim.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ProdutoRepository produtoRepository;
    private final ProdutoVendaRepository produtoVendaRepository;
    private final PagamentoRepository pagamentoRepository;
    private final ClienteRepository clienteRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final SerasaService serasaService;
    private final PessoaRepository pessoaRepository;

    public Venda realizarVendaVista(VendaVistaDTO dto) {

        Venda venda = new Venda();

        System.out.println("ID FUNCIONARIO RECEBIDO = " + dto.getIdFuncionario());
        Funcionario funcionario = funcionarioRepository
                .findById(dto.getIdFuncionario())
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        venda.setFuncionario(funcionario);
        venda.setStatusVenda(StatusVenda.PAGA);



        venda = vendaRepository.save(venda);

        BigDecimal total = BigDecimal.ZERO;

        for(ItensVendaDTO item : dto.getItens()) {

            Produto produto = produtoRepository.findById(item.getIdProduto())
                    .orElseThrow();

            ProdutoVenda pv = new ProdutoVenda();

            pv.setVenda(venda);
            pv.setProduto(produto);
            pv.setQuantidade(item.getQuantidade());
            pv.setPrecoUnitario(produto.getPrecoUnitario());

            produtoVendaRepository.save(pv);

            total = total.add(
                    produto.getPrecoUnitario()
                            .multiply(BigDecimal.valueOf(item.getQuantidade()))
            );
        }

        venda.setTotal(total);

        Pagamento pagamento = new Pagamento();

        pagamento.setVenda(venda);
        pagamento.setValorPagamento(total);
        pagamento.setMeioPagamento(MeioPagamento.valueOf(dto.getMeioPagamento()));

        pagamentoRepository.save(pagamento);

        return vendaRepository.save(venda);
    }

    public Venda realizarVendaFiada(VendaFiadoDTO dto) {
        if (dto.getCliente() == null) {
            throw new IllegalArgumentException("Cliente é obrigatório para venda fiada.");
        }

        Cliente cliente = clienteRepository
                .findByPessoaCpf(dto.getCliente().getCpf())
                .orElseGet(() -> {


                    Pessoa pessoa = new Pessoa();

                    pessoa.setNome(dto.getCliente().getNome());
                    pessoa.setCpf(dto.getCliente().getCpf());
                    pessoa.setEmail(dto.getCliente().getEmail());
                    pessoa.setTelefone(dto.getCliente().getTelefone());

                    Pessoa pessoaSalva = pessoaRepository.save(pessoa);

                    Cliente novoCliente = new Cliente();
                    novoCliente.setPessoa(pessoaSalva);

                    return clienteRepository.save(novoCliente);
                });

        boolean negativado = serasaService.consultarSerasa(cliente);

        if(negativado) {
            throw new ClienteNegativadoException(
                    "Cliente possui restrições junto ao Serasa."
            );
        }

        Venda venda = new Venda();

        venda.setCliente(cliente);
        Funcionario funcionario = funcionarioRepository
                .findById(dto.getIdFuncionario())
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        venda.setFuncionario(funcionario);
        venda.setStatusVenda(StatusVenda.PENDENTE);

        venda = vendaRepository.save(venda);

        BigDecimal total = BigDecimal.ZERO;

        for(ItensVendaDTO item : dto.getItens()) {

            Produto produto = produtoRepository.findById(item.getIdProduto())
                    .orElseThrow();

            ProdutoVenda pv = new ProdutoVenda();

            pv.setVenda(venda);
            pv.setProduto(produto);
            pv.setQuantidade(item.getQuantidade());
            pv.setPrecoUnitario(produto.getPrecoUnitario());

            produtoVendaRepository.save(pv);

            total = total.add(
                    produto.getPrecoUnitario()
                            .multiply(BigDecimal.valueOf(item.getQuantidade()))
            );
        }

        venda.setTotal(total);

        return vendaRepository.save(venda);
    }
}