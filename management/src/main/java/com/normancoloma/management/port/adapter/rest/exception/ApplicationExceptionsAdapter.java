package com.normancoloma.management.port.adapter.rest.exception;

import com.normancoloma.management.port.adapter.rest.response.RestErrorResponse;
import org.springframework.stereotype.Service;

@Service
public class ApplicationExceptionsAdapter {
    private static final String EXCEPTION_TEXT = "Exception";
    private static final String EMPTY_STRING = "";
    private static final String UPPERCASE_REGEX = "[A-Z]";
    private static final String POINT_STRING = ".";
    private static final int ZERO_INDEX = 0;

    RestErrorResponse adapt(Exception ex) {
        String code = ex.getClass().getSimpleName().replace(EXCEPTION_TEXT, EMPTY_STRING);
        String message = ex.getMessage();

        StringBuilder restCode = new StringBuilder();
        for (int charIndex = ZERO_INDEX; charIndex < code.length(); charIndex++) {
            final String currentChar = code.substring(charIndex, charIndex + 1);

            if (currentChar.matches(UPPERCASE_REGEX)) {
                restCode.append(charIndex > ZERO_INDEX ? POINT_STRING : EMPTY_STRING)
                        .append(currentChar.toLowerCase());
            } else {
                restCode.append(currentChar);
            }
        }

        return RestErrorResponse.builder()
                .code(restCode.toString())
                .message(message)
                .build();
    }
}
