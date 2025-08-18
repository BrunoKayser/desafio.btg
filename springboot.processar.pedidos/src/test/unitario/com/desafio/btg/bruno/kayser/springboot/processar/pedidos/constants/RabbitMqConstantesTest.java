package com.desafio.btg.bruno.kayser.springboot.processar.pedidos.constants;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RabbitMqConstantesTest {

    @Test
    void testarConstantes() {
        assertEquals("PEDIDO", RabbitMqConstantes.FILA_RECEBE_PEDIDO);
    }

}