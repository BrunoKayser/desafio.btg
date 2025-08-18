package com.desafio.btg.bruno.kayser.springboot.processar.pedidos.exceptions;

public class DatabaseException extends RuntimeException {

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

}
