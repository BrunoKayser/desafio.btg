package com.desafio.btg.bruno.kayser.springboot.processar.pedidos.mapper;

import com.desafio.btg.bruno.kayser.springboot.processar.pedidos.controller.response.PaginacaoResponse;
import com.desafio.btg.bruno.kayser.springboot.processar.pedidos.controller.response.PedidoPaginadoResponse;
import com.desafio.btg.bruno.kayser.springboot.processar.pedidos.controller.response.PedidoResponse;
import com.desafio.btg.bruno.kayser.springboot.processar.pedidos.entity.PedidoEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoResponseMapper {

    public PedidoPaginadoResponse toResponse(Page<PedidoEntity> pedidos) {
        return new PedidoPaginadoResponse(toPedidoResponse(pedidos.getContent()), toPaginacaoResponse(pedidos));
    }

    private List<PedidoResponse> toPedidoResponse(List<PedidoEntity> pedidoEntities) {
        return pedidoEntities
                .stream()
                .map(p -> new PedidoResponse(p.getCodigoCliente(), p.getCodigoPedido(), p.getTotal()))
                .toList();
    }

    private PaginacaoResponse toPaginacaoResponse(Page page) {
        return new PaginacaoResponse(page.getNumber(), page.getSize(), page.getTotalPages(), page.getTotalElements());
    }

}
