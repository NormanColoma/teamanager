package com.normancoloma.transfers.domain.model.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@AllArgsConstructor
@Data
public class Transaction {
    private UUID id;
    private UUID teamId;
    private UUID playerId;
    private float quantity;
    private Concept concept;
}

