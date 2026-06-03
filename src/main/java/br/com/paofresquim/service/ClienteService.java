package br.com.paofresquim.service;

import br.com.paofresquim.dto.request.ClienteRequest;
import br.com.paofresquim.dto.response.ClienteResponse;
import br.com.paofresquim.enums.EstadoCivil;
import br.com.paofresquim.model.Cliente;
import br.com.paofresquim.model.Pessoa;
import br.com.paofresquim.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Transactional(readOnly = true)
    public List<ClienteResponse> listarTodos() {
        return clienteRepository.findAll()
                .stream()
                .map(ClienteResponse::new)
                .toList();
    }


    @Transactional(readOnly = true)
    public ClienteResponse buscarPorId(Long id) throws Exception {
        Cliente cliente = buscarClienteOuLancarExcecao(id);
        return new ClienteResponse(cliente);
    }

    @Transactional
    public ClienteResponse criar(ClienteRequest request) {
        Cliente cliente = new Cliente();
        preencherDados(cliente, request);

        return new ClienteResponse(clienteRepository.save(cliente));
    }

    @Transactional
    public ClienteResponse atualizar(Long id, ClienteRequest request) throws Exception {
        Cliente cliente = buscarClienteOuLancarExcecao(id);

        preencherDados(cliente, request);

        return new ClienteResponse(clienteRepository.save(cliente));
    }

    @Transactional
    public void inativar(Long id) throws Exception {
        Cliente cliente = buscarClienteOuLancarExcecao(id);
        cliente.setInativo(false);
        clienteRepository.save(cliente);
    }

    @Transactional
    public void deletar(Long id) throws Exception {
        if (!clienteRepository.existsById(id)) {
            throw new Exception("Cliente com ID " + id + " não encontrado.");
        }
        clienteRepository.deleteById(id);
    }

    private Cliente buscarClienteOuLancarExcecao(Long id) throws Exception {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new Exception("Cliente com ID " + id + " não encontrado."));
    }

    private void preencherDados(Cliente cliente, ClienteRequest request) {
        Pessoa  pessoa = new Pessoa();
        pessoa.setNome(request.getNome());
        pessoa.setCpf(request.getCpf());
        pessoa.setDataNascimento(request.getDataNascimento());
        pessoa.setEmail(request.getEmail());
        pessoa.setTelefone(request.getTelefone());
        pessoa.setEndereco(request.getEndereco());
        pessoa.setEstadoCivil(EstadoCivil.valueOf(request.getEstadoCivil()));

        cliente.setPessoa(pessoa);

    }

}