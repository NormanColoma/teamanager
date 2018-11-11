package com.normancoloma.management.application.event;

import com.normancoloma.management.application.notification.Notification;
import com.normancoloma.management.domain.model.team.DomainEvent;
import com.normancoloma.management.domain.model.team.DomainEventEmitter;
import com.normancoloma.management.domain.model.team.DomainEventSubscriber;
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

    @Before("@annotation(EventSubscriber)")
    public void listen() {

        DomainEventEmitter
            .instance()
            .subscribe(new DomainEventSubscriber<DomainEvent>() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    DomainEvent domainEvent = (DomainEvent) evt.getNewValue();

                    Notification notification = Notification.builder()
                            .domainEvent(domainEvent)
                            .occurredOn(domainEvent.occurredOn())
                            .type(domainEvent.getClass().getName())
                            .build();
                    rabbitMQProducer.sendNotification(notification);
                }

                @Override
                public Class subscribedTo() {
                    return DomainEvent.class;
                }
            });
    }
}
