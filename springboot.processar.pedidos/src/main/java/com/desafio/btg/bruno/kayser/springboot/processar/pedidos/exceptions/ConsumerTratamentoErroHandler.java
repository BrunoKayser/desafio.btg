package com.desafio.btg.bruno.kayser.springboot.processar.pedidos.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.util.ErrorHandler;

@Slf4j
public class ConsumerTratamentoErroHandler implements ErrorHandler {

    @Override
    public void handleError(Throwable t) {
        var nomeFila = ((ListenerExecutionFailedException) t).getFailedMessage().getMessageProperties().getConsumerQueue();

        if(t.getCause() instanceof DatabaseException) {
            log.error("Fila: {}, Erro de banco de dados detectado, a mensagem deve retornar a fila.", nomeFila, t);
        } else {
            log.error("Fila: {}, Erro detectado, a mensagem não deve deve retornar a fila.", nomeFila, t);
            throw new AmqpRejectAndDontRequeueException("Não deve retornar a fila.", t);
        }
    }

}
