package com.normancoloma.management.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PlayerDoesNotExist extends RuntimeException {
    private String message;
}
