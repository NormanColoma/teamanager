package com.normancoloma.management.domain.model.team;

import lombok.Builder;

@Builder
public class Fund {
    @Builder.Default
    private double quantity = 0;
    private Currency currency;

    @Override
    public String toString() {
        return quantity + " " + currency.name();
    }
}