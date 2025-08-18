package com.desafio.btg.bruno.kayser.springboot.processar.pedidos.mapper;

import com.desafio.btg.bruno.kayser.springboot.processar.pedidos.entity.PedidoEntity;
import generic.fixture.GenericFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PedidoResponseMapperTest {

    @InjectMocks
    private PedidoResponseMapper pedidoResponseMapper;

    @Test
    void toPedidoResponse_deveMapearPedidoEntityParaPedidoResponse() {

        var pedidoEntities = new PageImpl<>(List.of(GenericFixture.generate(PedidoEntity.class)));

        var pedidoResponses = pedidoResponseMapper.toResponse(pedidoEntities);

        assertEquals(1, pedidoResponses.pedidos().size());
        assertEquals(pedidoEntities.getContent().getFirst().getCodigoCliente(), pedidoResponses.pedidos().getFirst().codigoCliente());
        assertEquals(pedidoEntities.getContent().getFirst().getCodigoPedido(), pedidoResponses.pedidos().getFirst().codigoPedido());
        assertEquals(pedidoEntities.getContent().getFirst().getTotal(), pedidoResponses.pedidos().getFirst().valor());
        assertEquals(pedidoEntities.getTotalPages(), pedidoResponses.page().totalPaginas());
        assertEquals(pedidoEntities.getTotalElements(), pedidoResponses.page().totalPedidos());
        assertEquals(pedidoEntities.getNumber(), pedidoResponses.page().pagina());
        assertEquals(pedidoEntities.getTotalPages(), pedidoResponses.page().totalPaginas());

    }

}