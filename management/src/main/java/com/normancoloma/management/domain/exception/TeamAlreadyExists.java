package com.normancoloma.management.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TeamAlreadyExists extends RuntimeException {
    private String message;
}
