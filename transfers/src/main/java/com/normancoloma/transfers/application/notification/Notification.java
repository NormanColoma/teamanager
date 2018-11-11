package com.normancoloma.transfers.application.notification;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class Notification {
    private Object domainEvent;
    private Date occurredOn;
    private String type;
}
