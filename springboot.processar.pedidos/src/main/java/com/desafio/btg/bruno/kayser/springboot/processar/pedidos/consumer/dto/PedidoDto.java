package com.desafio.btg.bruno.kayser.springboot.processar.pedidos.consumer.dto;

import java.io.Serializable;
import java.util.List;

public record PedidoDto(Long codigoPedido, Long codigoCliente, List<ItemDto> itens) implements Serializable {

}
