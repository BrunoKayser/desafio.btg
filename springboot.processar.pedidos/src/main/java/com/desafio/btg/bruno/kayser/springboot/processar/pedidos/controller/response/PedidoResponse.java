package com.desafio.btg.bruno.kayser.springboot.processar.pedidos.controller.response;

import java.math.BigDecimal;

public record PedidoResponse(Long codigoCliente, Long codigoPedido, BigDecimal valor) {}
