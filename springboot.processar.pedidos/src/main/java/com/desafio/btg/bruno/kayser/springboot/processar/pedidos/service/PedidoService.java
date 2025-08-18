package com.desafio.btg.bruno.kayser.springboot.processar.pedidos.service;

import com.desafio.btg.bruno.kayser.springboot.processar.pedidos.entity.PedidoEntity;
import com.desafio.btg.bruno.kayser.springboot.processar.pedidos.exceptions.DatabaseException;
import com.desafio.btg.bruno.kayser.springboot.processar.pedidos.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public void inserirPedido(PedidoEntity pedidoEntity) {

        pedidoEntity.calcularTotal();
        try {
            pedidoRepository.save(pedidoEntity);
        } catch(Exception e) {
            throw new DatabaseException("Erro ao inserir codigoPedido: ".concat(pedidoEntity.toString()), e);
        }
    }

   public Page<PedidoEntity> listaPaginada(Long codigoCliente, PageRequest paginacao) {
        try {
            return pedidoRepository.findAllByCodigoCliente(codigoCliente, paginacao);
        } catch (Exception e) {
            log.error("Erro interno de banco de dados: {}", codigoCliente, e);
            throw new DatabaseException("Erro ao listar pedidos do cliente com codigo: ".concat(codigoCliente.toString()), e);
        }
   }

}
