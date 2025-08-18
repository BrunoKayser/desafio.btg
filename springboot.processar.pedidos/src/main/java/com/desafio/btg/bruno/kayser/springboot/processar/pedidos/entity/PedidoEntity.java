package com.desafio.btg.bruno.kayser.springboot.processar.pedidos.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "itens")
@Entity
@Table(
        name = "pedido",
        indexes = {
                @Index(name = "codigo_cliente_index", columnList = "codigoCliente")
        }
)
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long codigoPedido;

    private Long codigoCliente;

    private BigDecimal total;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedidoEntity> itens = new ArrayList<>();

    public void calcularTotal() {
        this.total = itens
                .stream()
                .map(i -> i.getPreco().multiply(BigDecimal.valueOf(i.getQuantidade())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO).setScale(2, RoundingMode.HALF_EVEN);
    }

}
