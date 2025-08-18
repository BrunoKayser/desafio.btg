package com.desafio.btg.bruno.kayser.springboot.processar.pedidos.configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import static com.desafio.btg.bruno.kayser.springboot.processar.pedidos.constants.RabbitMqConstantes.FILA_RECEBE_PEDIDO;


@Component
public class RabbitMQConnection {

    private final static String NOME_EXCHANGE = "amq.direct";
    private AmqpAdmin amqpAdmin;

    public RabbitMQConnection(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    //Assim que a aclasse for construída, será executado a adição das filas
    @PostConstruct
    private void adiciona() {
        Queue filaPedido = this.fila(FILA_RECEBE_PEDIDO);

        DirectExchange troca = this.trocaDireta();

        Binding ligacao = this.relacionamento(filaPedido, troca);

        //Criação das filas
        this.amqpAdmin.declareQueue(filaPedido);

        this.amqpAdmin.declareExchange(troca);

        this.amqpAdmin.declareBinding(ligacao);
    }


    private Queue fila(String nomeFila) {
        return new Queue(nomeFila, true, false, false);
    }

    private DirectExchange trocaDireta() {
        return new DirectExchange(NOME_EXCHANGE);
    }

    private Binding relacionamento(Queue fila, DirectExchange troca) {
        return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);
    }

}
