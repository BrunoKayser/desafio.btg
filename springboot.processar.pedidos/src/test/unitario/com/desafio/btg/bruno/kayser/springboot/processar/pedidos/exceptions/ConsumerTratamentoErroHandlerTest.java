package com.desafio.btg.bruno.kayser.springboot.processar.pedidos.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
class ConsumerTratamentoErroHandlerTest {

    @InjectMocks
    private ConsumerTratamentoErroHandler consumerTratamentoErroHandler;

    private MessageProperties props;
    private Message message;

    @BeforeEach
    void setUp() {
        props = new MessageProperties();
        props.setConsumerQueue("FILA");
        message = new Message("msg".getBytes(), props);
    }

    @Test
    void handlerError_databaseExceptionNaoDeveRetornarAFila() {
        var excecao = new ListenerExecutionFailedException("Erro de banco de dados", new DatabaseException("Erro de banco de dados", new RuntimeException()), message);

        assertDoesNotThrow(() -> consumerTratamentoErroHandler.handleError(excecao));
    }

    @Test
    void handlerError_outroTipoDeExcecaoNaoDeveRetornarAFila() {
        var excecao = new ListenerExecutionFailedException("Erro de outro tipo", new RuntimeException("Erro de outro tipo"), message);

        assertThrows(AmqpRejectAndDontRequeueException.class, () -> consumerTratamentoErroHandler.handleError(excecao));
    }

}