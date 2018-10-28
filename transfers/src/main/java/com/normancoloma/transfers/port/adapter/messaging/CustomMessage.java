package com.normancoloma.transfers.port.adapter.messaging;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
public class CustomMessage {
    private UUID teamID;
    private float price;
}
