package com.normancoloma.management.port.adapter.rest.response;

import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Builder
@Data
public class TeamResponse {
    private UUID id;
    private String name;
    private String country;
    private Set<PlayerResponse> players;
    private String funds;
}
