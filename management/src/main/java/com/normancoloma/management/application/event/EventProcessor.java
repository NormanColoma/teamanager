package com.normancoloma.management.application.event;

import com.normancoloma.management.domain.model.team.DomainEvent;
import com.normancoloma.management.domain.model.team.DomainEventSubscriber;
import com.normancoloma.management.domain.model.team.DomainEventEmitter;
import com.normancoloma.management.domain.model.team.player.PlayerTransferred;
import com.normancoloma.management.port.adapter.messages.RabbitMQProducer;
import lombok.AllArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.beans.PropertyChangeEvent;

@Aspect
@Component
@AllArgsConstructor
public class EventProcessor {
    private final RabbitMQProducer rabbitMQProducer;

    @Before("@annotation(com.normancoloma.management.application.event.EventSubscriber)")
    public void listen() {
        DomainEventEmitter
            .instance()
            .subscribe(new DomainEventSubscriber<PlayerTransferred>() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    rabbitMQProducer.sendNotification(evt.getNewValue());
                }

                @Override
                public Class subscribedTo() {
                    return DomainEvent.class;
                }
            });
    }

}
