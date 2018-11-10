package com.normancoloma.management.domain.model.team.player;

import com.normancoloma.management.domain.model.team.DomainEvent;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Builder
@Data
public class PlayerTransferred implements DomainEvent {
    private UUID playerId;
    private UUID teamId;
    private float quantity;

    @Override
    public Date occurredOn() {
        return new Date();
    }
}
