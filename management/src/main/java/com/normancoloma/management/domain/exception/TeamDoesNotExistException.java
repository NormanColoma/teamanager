package com.normancoloma.management.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TeamDoesNotExistException extends RuntimeException {
    private String message;
}
