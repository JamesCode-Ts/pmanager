package com.java360.pmanager.domain.infrastructure.dto.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestError {

    private final String  errorCode;
    private final String errorMessage;
    private final int status;
    private final String path;

}
