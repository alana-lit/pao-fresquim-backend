package br.com.paofresquim.exception;

public class ClienteNegativadoException extends RuntimeException {

    public ClienteNegativadoException(String mensagem) {
        super(mensagem);
    }

}