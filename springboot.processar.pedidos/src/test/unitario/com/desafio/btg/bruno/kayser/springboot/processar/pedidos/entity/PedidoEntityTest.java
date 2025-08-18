package com.desafio.btg.bruno.kayser.springboot.processar.pedidos.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class PedidoEntityTest {

    @Test
    void calcularTotal() {
        var pedido = PedidoEntity.builder()
                .codigoPedido(1L)
                .codigoCliente(1L)
                .itens(List.of(
                        ItemPedidoEntity.builder().preco(BigDecimal.valueOf(10.00)).quantidade(2L).build(),
                        ItemPedidoEntity.builder().preco(BigDecimal.valueOf(5.00)).quantidade(3L).build()
                ))
                .build();
        pedido.calcularTotal();

        assertEquals(new BigDecimal("35.00"), pedido.getTotal());
    }
}