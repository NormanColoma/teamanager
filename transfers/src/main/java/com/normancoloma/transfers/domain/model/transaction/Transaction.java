package com.normancoloma.transfers.domain.model.transaction;

import lombok.Builder;

import java.util.UUID;

@Builder
public class Transaction {
    private UUID id;
    private UUID teamId;
    private UUID playerId;
    private float quantity;
    private Concept concept;
}

