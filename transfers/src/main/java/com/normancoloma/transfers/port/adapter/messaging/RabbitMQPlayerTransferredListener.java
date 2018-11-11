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

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.UUID;

@Component
@AllArgsConstructor
public class RabbitMQPlayerTransferredListener implements ExchangeListener {
    private final GenerateTransferTransaction generateTransferTransaction;

    @RabbitListener(
            bindings = @QueueBinding(value = @Queue(value = "player.transfers"),
                    exchange = @Exchange(value = "player.events", type = ExchangeTypes.FANOUT))
    )
    @Override
    public void filter(Notification notification) throws IOException {
        if(listenToMessagesOfType().contains(notification.getType())) {
            LinkedHashMap domainEvent = (LinkedHashMap) notification.getDomainEvent();

            GenerateTransferTransactionCommand command = GenerateTransferTransactionCommand.builder()
                    .playerId(UUID.fromString(domainEvent.get("playerId").toString()))
                    .teamId(UUID.fromString(domainEvent.get("teamId").toString()))
                    .quantity(Float.parseFloat(domainEvent.get("quantity").toString()))
                    .build();
            generateTransferTransaction.execute(command);
        }
    }

    @Override
    public Set<String> listenToMessagesOfType() {
        return new HashSet<>(Collections.singletonList("com.normancoloma.management.domain.model.team.player.PlayerTransferred"));
    }
}
