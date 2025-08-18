package com.desafio.btg.bruno.kayser.springboot.processar.pedidos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<ErroResponse> handlePedidoDatabaseException(DatabaseException ex) {

        var errorResponse = ErroResponse
                .builder()
                .dataHora(LocalDateTime.now())
                .mensagem(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErroResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {

        var errorResponse = ErroResponse
                .builder()
                .dataHora(LocalDateTime.now())
                .mensagem(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

}
