package br.com.paofresquim.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {

    private Integer id;
    private String usuario;
    private String nivelAcesso;

}