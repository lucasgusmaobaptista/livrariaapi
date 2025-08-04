package me.lucasgusmao.livraria_api.exceptions;

import lombok.Getter;

@Getter
public class CampoInvalidoException extends RuntimeException {

    private String campo;

    public  CampoInvalidoException(String campo, String message) {
        super(message);
        this.campo = campo;

    }
}
