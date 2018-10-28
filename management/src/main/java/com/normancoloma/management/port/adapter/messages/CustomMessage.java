package com.normancoloma.management.port.adapter.messages;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
public class CustomMessage {
    private UUID teamID;
    private float price;
}