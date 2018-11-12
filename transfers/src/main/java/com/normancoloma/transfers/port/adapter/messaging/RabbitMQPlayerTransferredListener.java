package com.normancoloma.transfers.port.adapter.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.normancoloma.transfers.application.notification.Notification;
import com.normancoloma.transfers.application.usecase.GenerateTransferTransaction;
import com.normancoloma.transfers.application.usecase.GenerateTransferTransactionCommand;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class RabbitMQPlayerTransferredListener implements ExchangeListener {
    private final GenerateTransferTransaction generateTransferTransaction;
    private final ObjectMapper objectMapper;

    @RabbitListener(
            bindings = @QueueBinding(value = @Queue(value = "player.transfers"),
                    exchange = @Exchange(value = "player.events", type = ExchangeTypes.FANOUT))
    )
    @Override
    public void filter(Notification notification) {
        if(listenToMessagesOfType().contains(notification.getType())) {
            GenerateTransferTransactionCommand generateTransferTransactionCommand = objectMapper
                    .convertValue(notification.getDomainEvent(), GenerateTransferTransactionCommand.class);

            generateTransferTransaction.execute(generateTransferTransactionCommand);
        }
    }

    @Override
    public Set<String> listenToMessagesOfType() {
        return new HashSet<>(Collections.singletonList("com.normancoloma.management.domain.model.team.player.PlayerTransferred"));
    }
}
