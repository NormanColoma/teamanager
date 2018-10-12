package com.normancoloma.management.port.adapter.rest.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class PlayerResponse {
    private UUID id;
    private String name;
    private Byte years;
    private Byte shirtNumber;
}
