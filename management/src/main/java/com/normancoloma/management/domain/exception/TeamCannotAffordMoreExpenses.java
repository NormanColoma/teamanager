package com.normancoloma.management.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeamCannotAffordMoreExpenses extends RuntimeException {
    private String message;
}
