package com.normancoloma.management.domain.model.team;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Fund {
    @Builder.Default
    private double quantity = 0;
    private Currency currency;

    @Override
    public String toString() {
        return quantity + " " + currency.name();
    }

    void subtract(float quantity) {
        this.quantity -= quantity;
    }
}