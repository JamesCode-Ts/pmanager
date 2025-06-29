package com.java360.pmanager.domain.infrastructure.dto.exception;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RestError {

    private final String  errorCode;
    private final String errorMessage;
    private final List<String> details;
    private final int status;
    private final String path;

}
