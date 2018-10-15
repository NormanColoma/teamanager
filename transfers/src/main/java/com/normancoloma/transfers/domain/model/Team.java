package com.normancoloma.transfers.domain.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class Team {
    private UUID id;
    private Fund funds;
    private float revenues;
    private float costs;

    public void addRevenues(float revenues) {
        this.revenues += revenues;
        calculateFunds();
    }

    public void addCosts(float costs) {
        this.costs += costs;
        calculateFunds();
    }

    private void calculateFunds() {
        funds.setQuantity(revenues - costs);
    }
}
