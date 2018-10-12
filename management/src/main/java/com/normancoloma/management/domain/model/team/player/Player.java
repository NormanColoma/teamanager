package com.normancoloma.management.domain.model.team.player;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@Data
public class Player {
    private UUID id;
    private String name;
    private byte years;
    private byte shirtNumber;

    public boolean hasShirtNumber(byte shirtNumber) {
        return Objects.equals(this.shirtNumber, shirtNumber);
    }

    public boolean isSame(UUID id) {
        return Objects.equals(this.id, id);
    }

    public boolean isSame(String name, byte years) {
        return Objects.equals(this.name, name) && Objects.equals(this.years, years);
    }
}
