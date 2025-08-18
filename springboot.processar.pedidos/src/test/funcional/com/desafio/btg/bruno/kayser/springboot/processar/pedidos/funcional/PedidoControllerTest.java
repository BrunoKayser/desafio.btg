package com.desafio.btg.bruno.kayser.springboot.processar.pedidos.funcional;

import com.desafio.btg.bruno.kayser.springboot.processar.pedidos.Application;
import com.desafio.btg.bruno.kayser.springboot.processar.pedidos.entity.ItemPedidoEntity;
import com.desafio.btg.bruno.kayser.springboot.processar.pedidos.entity.PedidoEntity;
import com.desafio.btg.bruno.kayser.springboot.processar.pedidos.service.PedidoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = Application.class)
@AutoConfigureMockMvc
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PedidoService pedidoService;

    @Test
    void deveRetornarPedidosPorCliente() throws Exception {
        var pedidoEntity = criarPedidoReal();
        pedidoEntity.getItens().getFirst().setPedido(pedidoEntity);

        pedidoService.inserirPedido(pedidoEntity);

        mockMvc.perform(get("/cliente/{codigoCliente}/pedidos", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pedidos[0].codigoPedido").value(1000))
                .andExpect(jsonPath("$.pedidos[0].codigoCliente").value(1L))
                .andExpect(jsonPath("$.pedidos[0].valor").value(10.00))
                .andExpect(jsonPath("$.page.pagina").value(0))
                .andExpect(jsonPath("$.page.tamanhoPagina").value(10))
                .andExpect(jsonPath("$.page.totalPaginas").value(1))
                .andExpect(jsonPath("$.page.totalPedidos").value(1));
    }

    private static PedidoEntity criarPedidoReal() {
        return PedidoEntity
                .builder()
                .codigoCliente(1L)
                .codigoPedido(1000L)
                .itens(List.of(ItemPedidoEntity
                        .builder()
                        .preco(BigDecimal.ONE)
                        .quantidade(10L)
                        .produto("Lápis")
                        .build())
                )
                .build();
    }

}
