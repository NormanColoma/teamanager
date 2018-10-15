package com.normancoloma.management.domain.model.team.player;

import com.normancoloma.management.domain.model.team.Currency;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Salary {
    private float quantity;
    private Currency currency;
}
