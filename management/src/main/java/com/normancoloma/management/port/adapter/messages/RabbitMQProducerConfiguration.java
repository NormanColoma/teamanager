package com.normancoloma.management.port.adapter.messages;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;

public class RabbitMQProducerConfiguration {
   /* @Bean
    DirectExchange exchange() {
        return new DirectExchange(directExchange);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory)
    {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setExchange(directExchange);
        template.setRoutingKey(routingKey);
        template.setConnectionFactory(connectionFactory);
        return template;
    }*/
}
