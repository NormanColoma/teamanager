package com.normancoloma.transfers.port.adapter.messaging;

import com.normancoloma.transfers.application.notification.Notification;

import java.io.IOException;
import java.util.Set;

public interface ExchangeListener {
    void filter(Notification incomingNotification) throws IOException;
    Set<String> listenToMessagesOfType();
}
