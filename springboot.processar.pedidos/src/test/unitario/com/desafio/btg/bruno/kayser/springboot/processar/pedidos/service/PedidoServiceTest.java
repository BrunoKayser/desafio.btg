package com.desafio.btg.bruno.kayser.springboot.processar.pedidos.service;

import com.desafio.btg.bruno.kayser.springboot.processar.pedidos.entity.PedidoEntity;
import com.desafio.btg.bruno.kayser.springboot.processar.pedidos.exceptions.DatabaseException;
import com.desafio.btg.bruno.kayser.springboot.processar.pedidos.repository.PedidoRepository;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @InjectMocks
    private PedidoService pedidoService;

    @Mock
    private PedidoRepository pedidoRepository;

    private PedidoEntity pedidoEntity = GenericFixture.generate(PedidoEntity.class);

    @Test
    void inserirPedido_deveSalvarPedido() {

        pedidoService.inserirPedido(pedidoEntity);

        verify(pedidoRepository, times(1)).save(pedidoEntity);

    }

    @Test
    void inserirPedido_deveLancarDatabaseExceptionQuandoErroNoBancoDeDados() {
        when(pedidoRepository.save(pedidoEntity)).thenThrow(new RuntimeException("Erro no banco de dados"));

        var excecao = assertThrows(DatabaseException.class, () -> pedidoService.inserirPedido(pedidoEntity));

        verify(pedidoRepository, times(1)).save(pedidoEntity);

        assertEquals("Erro ao inserir codigoPedido: ".concat(pedidoEntity.toString()), excecao.getMessage());

    }

    @Test
    void listaPaginada_deveListarPedidosPaginados() {
        var codigoCliente = 1L;
        var paginacao = PageRequest.of(10, 10);
        var pedidoEntityPaginado = new PageImpl<>(List.of(pedidoEntity));

        when(pedidoRepository.findAllByCodigoCliente(codigoCliente, paginacao))
                .thenReturn(pedidoEntityPaginado);

        var retorno = pedidoService.listaPaginada(codigoCliente, paginacao);

        verify(pedidoRepository, times(1)).findAllByCodigoCliente(codigoCliente, paginacao);

        assertEquals(pedidoEntityPaginado, retorno);
    }

}