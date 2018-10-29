package com.normancoloma.transfers.port.adapter.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.normancoloma.transfers.application.usecase.GenerateTransferTransaction;
import com.normancoloma.transfers.application.usecase.GenerateTransferTransactionCommand;
import com.normancoloma.transfers.domain.model.transaction.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RabbitMQPlayerTransferredListener {
    private final GenerateTransferTransaction generateTransferTransaction;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "player.transfers"),
            exchange = @Exchange(value = "player.events"),
            key = "player.transferred")
    )
    public void receiveMessage(GenerateTransferTransactionCommand message)
    {
        generateTransferTransaction.execute(message);
    }
}
