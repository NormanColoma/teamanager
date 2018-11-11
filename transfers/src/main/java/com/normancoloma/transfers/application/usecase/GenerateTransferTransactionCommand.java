package com.normancoloma.transfers.application.usecase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@Builder
public class GenerateTransferTransactionCommand {
    private UUID playerId;
    private UUID teamId;
    private float quantity;
}
