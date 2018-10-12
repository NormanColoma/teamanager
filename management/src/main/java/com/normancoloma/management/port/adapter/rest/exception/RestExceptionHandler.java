package com.normancoloma.management.port.adapter.rest.exception;

import com.normancoloma.management.domain.exception.PlayerAlreadyBelongsToTeamException;
import com.normancoloma.management.domain.exception.PlayerDoesNotExist;
import com.normancoloma.management.domain.exception.TeamAlreadyExists;
import com.normancoloma.management.domain.exception.TeamDoesNotExistException;
import com.normancoloma.management.port.adapter.rest.response.RestErrorResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
@AllArgsConstructor
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    private final ApplicationExceptionsAdapter applicationExceptionsAdapter;

    @ExceptionHandler(value = {PlayerDoesNotExist.class, TeamDoesNotExistException.class})
    public ResponseEntity<Object> handleEntityNotFound(
            Exception ex, WebRequest request) {

        RestErrorResponse body = applicationExceptionsAdapter.adapt(ex);

        log.error("Error message", ex);
        return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {PlayerAlreadyBelongsToTeamException.class, TeamAlreadyExists.class})
    public ResponseEntity<Object> handleInternalServerError(
            Exception ex, WebRequest request) {

        RestErrorResponse body = applicationExceptionsAdapter.adapt(ex);

        log.error("Error message", ex);
        return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
