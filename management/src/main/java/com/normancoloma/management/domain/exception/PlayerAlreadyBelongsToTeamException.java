package com.normancoloma.management.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PlayerAlreadyBelongsToTeamException extends RuntimeException {
    private String message;
}
