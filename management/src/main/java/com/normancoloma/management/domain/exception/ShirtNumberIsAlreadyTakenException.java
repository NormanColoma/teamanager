package com.normancoloma.management.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ShirtNumberIsAlreadyTakenException extends RuntimeException {
    private String message;
}
