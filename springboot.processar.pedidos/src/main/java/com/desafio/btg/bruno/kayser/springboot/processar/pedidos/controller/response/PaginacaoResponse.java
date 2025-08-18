package com.desafio.btg.bruno.kayser.springboot.processar.pedidos.controller.response;

public record PaginacaoResponse(Integer pagina, Integer tamanhoPagina, Integer totalPaginas, Long totalPedidos) {}
