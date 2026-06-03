package br.com.paofresquim.service;

import br.com.paofresquim.model.Cliente;
import br.com.paofresquim.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class SerasaService {

    private final ClienteRepository clienteRepository;

    public boolean consultarSerasa(Cliente cliente) {

        boolean negativado = new Random().nextBoolean();

        cliente.setEstadoSerasa(negativado);

        clienteRepository.save(cliente);

        return negativado;
    }

}