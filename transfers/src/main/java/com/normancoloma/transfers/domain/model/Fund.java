package com.normancoloma.transfers.domain.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Fund {
    @Builder.Default
    private float quantity = 0;
    private Currency currency;

    @Override
    public String toString() {
        return quantity + " " + currency.name();
    }
}
