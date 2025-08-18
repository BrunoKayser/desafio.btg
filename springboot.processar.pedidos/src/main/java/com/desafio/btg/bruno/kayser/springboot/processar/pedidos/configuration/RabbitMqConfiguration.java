package com.desafio.btg.bruno.kayser.springboot.processar.pedidos.configuration;

import com.desafio.btg.bruno.kayser.springboot.processar.pedidos.exceptions.ConsumerTratamentoErroHandler;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.DirectRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.DirectMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {

    @Bean
    public RabbitListenerContainerFactory<DirectMessageListenerContainer> rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        var factory = new DirectRabbitListenerContainerFactory();
        factory.setMessageConverter(converter());
        factory.setConnectionFactory(connectionFactory);
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);

        //factory.setPrefetchCount(300);  Quantidade máxima de mensagens que o rabbitMq processa de uma única vez

        factory.setErrorHandler(new ConsumerTratamentoErroHandler());

        return factory;
    }

    private Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

}
