package com.desafio.btg.bruno.kayser.springboot.processar.pedidos.mapper;

import com.desafio.btg.bruno.kayser.springboot.processar.pedidos.consumer.dto.ItemDto;
import com.desafio.btg.bruno.kayser.springboot.processar.pedidos.consumer.dto.PedidoDto;
import com.desafio.btg.bruno.kayser.springboot.processar.pedidos.entity.ItemPedidoEntity;
import com.desafio.btg.bruno.kayser.springboot.processar.pedidos.entity.PedidoEntity;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {

    public PedidoEntity toEntity(PedidoDto pedidoDto) {

        var pedido = PedidoEntity
                .builder()
                .codigoCliente(pedidoDto.codigoCliente())
                .codigoPedido(pedidoDto.codigoPedido())
                .itens(pedidoDto.itens().stream().map(this::toEntity).toList())
                .build();

        pedido.getItens().forEach(itemPedidoEntity -> itemPedidoEntity.setPedido(pedido));

        return pedido;
    }

    private ItemPedidoEntity toEntity(ItemDto itemDto) {
        return ItemPedidoEntity
                .builder()
                .preco(itemDto.preco())
                .produto(itemDto.produto())
                .quantidade(itemDto.quantidade())
                .build();
    }

}
