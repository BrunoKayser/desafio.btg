package com.desafio.btg.bruno.kayser.springboot.processar.pedidos.exceptions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Test
    public void handlePedidoDatabaseException() {

        var exception = new DatabaseException("Erro de banco de dados", new RuntimeException("Causa do erro"));

        var resultado = globalExceptionHandler.handlePedidoDatabaseException(exception);

        assertEquals("Erro de banco de dados", resultado.getBody().getMensagem());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), resultado.getStatusCodeValue());
        assertNotNull(resultado.getBody().getDataHora());
    }

    @Test
    public void handleMethodArgumentTypeMismatchException() {

        var exception = new MethodArgumentTypeMismatchException(
                null, Long.class, "codigoCliente", null, new RuntimeException("Tipo de argumento inválido"));

        var resultado = globalExceptionHandler.handleMethodArgumentTypeMismatchException(exception);

        assertEquals("Method parameter 'codigoCliente': Failed to convert value of type 'null' to required type 'java.lang.Long'; Tipo de argumento inválido", resultado.getBody().getMensagem());
        assertEquals(HttpStatus.BAD_REQUEST.value(), resultado.getStatusCodeValue());
        assertNotNull(resultado.getBody().getDataHora());
    }

}