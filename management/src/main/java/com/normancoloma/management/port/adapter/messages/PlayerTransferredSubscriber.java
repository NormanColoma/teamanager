package com.normancoloma.management.port.adapter.messages;

import com.normancoloma.management.domain.model.team.DomainEventSubscriber;
import com.normancoloma.management.domain.model.team.player.PlayerTransferred;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

@AllArgsConstructor
@Data
@Component
public class PlayerTransferredSubscriber implements PropertyChangeListener, DomainEventSubscriber {
    private final String EXCHANGE_NAME = "player.events";
    private final String ROUTING_KEY = "";
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, evt.getNewValue());
    }

    @Override
    public Class subscribedTo() {
        return PlayerTransferred.class;
    }
}
