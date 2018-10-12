package com.normancoloma.management.port.adapter.rest.request;

import lombok.Getter;

import java.util.UUID;

@Getter
public class TransferPlayerRequest {
    private UUID playerToBeTransferred;
    private UUID teamAcquiringPlayer;
}
