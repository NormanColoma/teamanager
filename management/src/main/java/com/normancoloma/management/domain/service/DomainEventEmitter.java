package com.normancoloma.management.domain.service;

public interface DomainEventEmitter {
    void emit(String message);
}
