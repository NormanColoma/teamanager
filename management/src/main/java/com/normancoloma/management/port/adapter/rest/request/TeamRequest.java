package com.normancoloma.management.port.adapter.rest.request;

import lombok.Getter;

@Getter
public class TeamRequest {
    private String name;
    private String country;
    private Double funds;
    private String currency;
}
