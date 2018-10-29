package com.normancoloma.management.domain.model.team.player;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
public class PlayerTransferred {
    private UUID playerId;
    private UUID teamId;
    private float quantity;
}
