package com.desafio.btg.bruno.kayser.springboot.processar.pedidos.consumer;

import com.desafio.btg.bruno.kayser.springboot.processar.pedidos.consumer.dto.PedidoDto;
import com.desafio.btg.bruno.kayser.springboot.processar.pedidos.mapper.PedidoMapper;
import com.desafio.btg.bruno.kayser.springboot.processar.pedidos.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.desafio.btg.bruno.kayser.springboot.processar.pedidos.constants.RabbitMqConstantes.FILA_RECEBE_PEDIDO;

@Component
@RequiredArgsConstructor
public class PedidoConsumer {

    private final PedidoService pedidoService;
    private final PedidoMapper pedidoMapper;

    @RabbitListener(queues = FILA_RECEBE_PEDIDO)
    public void consume(PedidoDto pedidoDto){
        var pedido = pedidoMapper.toEntity(pedidoDto);

        pedidoService.inserirPedido(pedido);

    }

}
