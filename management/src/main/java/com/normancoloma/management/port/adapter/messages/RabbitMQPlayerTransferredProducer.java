package com.normancoloma.management.port.adapter.messages;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@AllArgsConstructor
public class RabbitMQPlayerTransferredProducer  {
    private final String directExchange = "player.events";
    private final String routingKey = "player.transferred";
    private final RabbitTemplate rabbitTemplate;
}
