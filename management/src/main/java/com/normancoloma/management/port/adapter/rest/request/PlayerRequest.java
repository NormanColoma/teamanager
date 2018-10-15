package com.normancoloma.management.port.adapter.rest.request;

import lombok.Getter;

@Getter
public class PlayerRequest {
    private String name;
    private byte years;
    private byte shirtNumber;
    private float salary;
}
