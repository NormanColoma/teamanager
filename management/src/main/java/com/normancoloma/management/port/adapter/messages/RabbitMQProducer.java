package com.normancoloma.management.port.adapter.messages;

import com.normancoloma.management.domain.service.DomainEventEmitter;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RabbitMQProducer implements DomainEventEmitter {
    private final String directExchange = "player.events";
    private final String routingKey = "player.transferred";
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void emit(String message) {
        rabbitTemplate.convertAndSend(directExchange, routingKey, message);
    }
}
