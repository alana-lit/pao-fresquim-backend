package br.com.paofresquim.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    private String usuario;
    private String senha;

}