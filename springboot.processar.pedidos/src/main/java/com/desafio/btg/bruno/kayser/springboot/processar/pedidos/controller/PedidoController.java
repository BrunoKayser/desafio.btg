package com.desafio.btg.bruno.kayser.springboot.processar.pedidos.controller;

import com.desafio.btg.bruno.kayser.springboot.processar.pedidos.controller.response.PedidoPaginadoResponse;
import com.desafio.btg.bruno.kayser.springboot.processar.pedidos.mapper.PedidoResponseMapper;
import com.desafio.btg.bruno.kayser.springboot.processar.pedidos.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PedidoController {

    private final PedidoService pedidoService;
    private final PedidoResponseMapper pedidoResponseMapper;

    @GetMapping(value = "/cliente/{codigoCliente}/pedidos")
    public ResponseEntity<PedidoPaginadoResponse> listarPedidos(@RequestParam(name = "pagina", defaultValue = "0") Integer pagina,
                                                                @RequestParam(name = "tamanhoPagina", defaultValue = "10") Integer tamanhoPagina,
                                                                @PathVariable(name = "codigoCliente") Long codigoCliente) {

        var pedidos = pedidoService.listaPaginada(codigoCliente, PageRequest.of(pagina, tamanhoPagina));
        
        return new ResponseEntity<>(pedidoResponseMapper.toResponse(pedidos), HttpStatus.OK);

    }

}
