package com.normancoloma.management.domain.model.team;

import java.beans.PropertyChangeListener;

public interface DomainEventSubscriber<T> extends PropertyChangeListener {
    Class subscribedTo();
}
