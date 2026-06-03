package br.com.paofresquim.service;

import br.com.paofresquim.dto.request.LoginRequest;
import br.com.paofresquim.dto.response.LoginResponse;
import br.com.paofresquim.model.Usuario;
import br.com.paofresquim.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UsuarioRepository usuarioRepository;

    public LoginResponse login(LoginRequest request) {

        Usuario usuario = usuarioRepository
                .findByUsuario(request.getUsuario())
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado"));

        if (!usuario.getSenha().equals(request.getSenha())) {
            throw new RuntimeException("Senha inválida");
        }

        return new LoginResponse(
                usuario.getId(),
                usuario.getUsuario(),
                usuario.getNivelAcesso().name()
        );
    }
}
