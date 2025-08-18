package com.desafio.btg.bruno.kayser.springboot.processar.pedidos.consumer.dto;

import java.math.BigDecimal;

public record ItemDto (String produto, Long quantidade, BigDecimal preco) {
}
