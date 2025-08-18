package com.desafio.btg.bruno.kayser.springboot.processar.pedidos.controller;

import com.desafio.btg.bruno.kayser.springboot.processar.pedidos.controller.response.PedidoPaginadoResponse;
import com.desafio.btg.bruno.kayser.springboot.processar.pedidos.entity.PedidoEntity;
import com.desafio.btg.bruno.kayser.springboot.processar.pedidos.mapper.PedidoResponseMapper;
import com.desafio.btg.bruno.kayser.springboot.processar.pedidos.service.PedidoService;
import generic.fixture.GenericFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PedidoControllerTest {

    @InjectMocks
    private PedidoController pedidoController;

    @Mock
    private PedidoService pedidoService;

    @Mock
    private PedidoResponseMapper pedidoResponseMapper;

    @Test
    void listarPedidos_deveRetornarPedidosPaginados() {
        Long codigoCliente = 1L;
        int pagina = 0;
        int tamanhoPagina = 10;

        var pageRequest = PageRequest.of(pagina, tamanhoPagina);
        var pedidoEntityPaginado = new PageImpl<>(List.of(GenericFixture.generate(PedidoEntity.class)));
        var responseMock = GenericFixture.generate(PedidoPaginadoResponse.class);

        when(pedidoService.listaPaginada(codigoCliente, pageRequest)).thenReturn(pedidoEntityPaginado);
        when(pedidoResponseMapper.toResponse(pedidoEntityPaginado)).thenReturn(responseMock);

        var response = pedidoController.listarPedidos(pagina, tamanhoPagina, codigoCliente);

        verify(pedidoService, times(1)).listaPaginada(codigoCliente, pageRequest);
        verify(pedidoResponseMapper, times(1)).toResponse(pedidoEntityPaginado);

        assertEquals(org.springframework.http.HttpStatus.OK, response.getStatusCode());
        assertEquals(responseMock, response.getBody());
    }
}