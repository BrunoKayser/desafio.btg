package com.desafio.btg.bruno.kayser.springboot.processar.pedidos.repository;

import com.desafio.btg.bruno.kayser.springboot.processar.pedidos.entity.PedidoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {

    Page<PedidoEntity> findAllByCodigoCliente(Long codigoCliente, PageRequest paginacao);

}
