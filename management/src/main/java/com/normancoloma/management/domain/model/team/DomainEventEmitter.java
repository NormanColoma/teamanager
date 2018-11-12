package com.normancoloma.management.domain.model.team;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Objects;

import static java.util.Arrays.asList;

@AllArgsConstructor
@Component
public class DomainEventEmitter {
    private String eventPublished;
    private static PropertyChangeSupport support;

    private static final ThreadLocal<DomainEventEmitter> instance = new ThreadLocal<DomainEventEmitter>() {
        protected DomainEventEmitter initialValue() {
            support = new PropertyChangeSupport(this);
            return new DomainEventEmitter();
        }
    };

    public static DomainEventEmitter instance() {
        return instance.get();
    }

    public DomainEventEmitter() {
        support = new PropertyChangeSupport(this);
    }

    public void subscribe(PropertyChangeListener subscriber) {
        support.addPropertyChangeListener(subscriber);
    }


    void publish(DomainEvent domainEvent){
        asList(support.getPropertyChangeListeners())
            .forEach(listener -> {
                if (isSubscribedToEvent(((DomainEventSubscriber) listener).subscribedTo(), domainEvent.getClass())) {
                    support.firePropertyChange("eventPublished", eventPublished, domainEvent);
                }
            }
        );
    }

    public void reset() {
        asList(support.getPropertyChangeListeners())
                .forEach(this::unsubscribe);
    }

    private void unsubscribe(PropertyChangeListener subscriber) {
        support.removePropertyChangeListener(subscriber);
    }

    private boolean isSubscribedToEvent(Class listenerEventClass, Class incomingEventClass){
        return Objects.equals(listenerEventClass, incomingEventClass) ||
                Objects.equals(listenerEventClass, DomainEvent.class);
    }
}
