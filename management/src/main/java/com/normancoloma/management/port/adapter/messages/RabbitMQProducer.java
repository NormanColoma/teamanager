package com.normancoloma.management.port.adapter.messages;

import com.normancoloma.management.application.notification.Notification;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RabbitMQProducer {
    private final RabbitTemplate rabbitTemplate;

    public void sendNotification(Notification notification) {
        String EXCHANGE_NAME = "player.events";
        String ROUTING_KEY = "";
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, notification);
    }
}
