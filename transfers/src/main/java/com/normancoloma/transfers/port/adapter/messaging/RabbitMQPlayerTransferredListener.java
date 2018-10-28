package com.normancoloma.transfers.port.adapter.messaging;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQPlayerTransferredListener {
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "player.transfers"),
            exchange = @Exchange(value = "player.events"),
            key = "player.transferred")
    )
    public void receiveMessage(CustomMessage message)
    {
        System.out.println(message);
    }
}
