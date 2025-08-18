package com.desafio.btg.bruno.kayser.springboot.processar.pedidos.consumer;

import com.desafio.btg.bruno.kayser.springboot.processar.pedidos.consumer.dto.PedidoDto;
import com.desafio.btg.bruno.kayser.springboot.processar.pedidos.entity.PedidoEntity;
import com.desafio.btg.bruno.kayser.springboot.processar.pedidos.mapper.PedidoMapper;
import com.desafio.btg.bruno.kayser.springboot.processar.pedidos.service.PedidoService;
import generic.fixture.GenericFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PedidoConsumerTest {

    @InjectMocks
    private PedidoConsumer pedidoConsumer;

    @Mock
    private PedidoService pedidoService;

    @Mock
    private PedidoMapper pedidoMapper;

    @Test
    public void consume_deveConsumirPedidoComSucesso() {

        var pedidoDto = GenericFixture.generate(PedidoDto.class);
        var pedidoEntity = GenericFixture.generate(PedidoEntity.class);

        when(pedidoMapper.toEntity(pedidoDto)).thenReturn(pedidoEntity);

        assertDoesNotThrow(() -> pedidoConsumer.consume(pedidoDto));

        verify(pedidoService, times(1)).inserirPedido(pedidoEntity);

    }

}