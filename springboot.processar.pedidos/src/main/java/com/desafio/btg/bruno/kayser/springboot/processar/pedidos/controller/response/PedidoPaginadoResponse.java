package com.desafio.btg.bruno.kayser.springboot.processar.pedidos.controller.response;

import java.util.List;

public record PedidoPaginadoResponse(List<PedidoResponse> pedidos, PaginacaoResponse page) {}
