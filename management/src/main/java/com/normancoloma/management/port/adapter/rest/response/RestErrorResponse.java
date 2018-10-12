package com.normancoloma.management.port.adapter.rest.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RestErrorResponse {
    private String code;
    private String message;
}