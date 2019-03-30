package com.colaborador.exception;

public class ColaboradorException extends RuntimeException {

    private final String message;

    public ColaboradorException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
