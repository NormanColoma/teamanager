package com.normancoloma.management.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PlayerDoesNotBelongToGivenTeam extends RuntimeException {
    private String message;
}
