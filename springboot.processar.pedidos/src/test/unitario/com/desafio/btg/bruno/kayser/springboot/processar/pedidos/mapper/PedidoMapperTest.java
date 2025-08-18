package com.desafio.btg.bruno.kayser.springboot.processar.pedidos.mapper;

import com.desafio.btg.bruno.kayser.springboot.processar.pedidos.consumer.dto.ItemDto;
import com.desafio.btg.bruno.kayser.springboot.processar.pedidos.consumer.dto.PedidoDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PedidoMapperTest {

    @InjectMocks
    private PedidoMapper pedidoMapper;

    @Test
    void toEntity_deveMapearPedidoDtoParaPedidoEntity() {

        var pedidoDto = new PedidoDto(1L, 123L,
                List.of(
                        new ItemDto("Produto A", 10L, new BigDecimal(2)),
                        new ItemDto("Produto B", 20L, new BigDecimal(1))
                ));

        var pedidoEntity = pedidoMapper.toEntity(pedidoDto);

        assertEquals(pedidoDto.codigoCliente(), pedidoEntity.getCodigoCliente());
        assertEquals(pedidoDto.codigoPedido(), pedidoEntity.getCodigoPedido());
        assertEquals(pedidoDto.itens().size(), pedidoEntity.getItens().size());
        assertEquals(pedidoDto.itens().getFirst().produto(), pedidoEntity.getItens().getFirst().getProduto());
        assertEquals(pedidoDto.itens().getFirst().preco(), pedidoEntity.getItens().getFirst().getPreco());
        assertEquals(pedidoDto.itens().getFirst().quantidade(), pedidoEntity.getItens().getFirst().getQuantidade());
        assertEquals(pedidoDto.itens().get(1).produto(), pedidoEntity.getItens().get(1).getProduto());
        assertEquals(pedidoDto.itens().get(1).preco(), pedidoEntity.getItens().get(1).getPreco());
        assertEquals(pedidoDto.itens().get(1).quantidade(), pedidoEntity.getItens().get(1).getQuantidade());
    }

}