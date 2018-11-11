package com.normancoloma.management.application.notification;

import com.normancoloma.management.domain.model.team.DomainEvent;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class Notification {
    private DomainEvent domainEvent;
    private Date occurredOn;
    private String type;
}
