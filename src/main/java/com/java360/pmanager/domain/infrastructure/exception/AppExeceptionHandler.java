package com.java360.pmanager.domain.infrastructure.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Objects;

@ControllerAdvice
public class AppExeceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = RequestException.class)
    public ResponseEntity<Object> handleRequestException(RequestException ex, WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;

        return handleException(ex, ex.getErrorCode(), ex.getMessage(), null, HttpStatus.BAD_REQUEST,request);

    }



    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;

        return handleException(ex, null, ex.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR, request);

    }
   //"Making ControllerAdvice work with the Bean Validation API".
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {

      List<String> details = ex
              .getBindingResult()
              .getFieldErrors()
              .stream()
              .filter(Objects::nonNull)
              .map(DefaultMessageSourceResolvable::getDefaultMessage)
              .toList();

              return  handleException(ex, "ValidationError", null, details, HttpStatus.BAD_REQUEST, request);
    }

    private ResponseEntity<Object> handleException(
            Exception ex,
            String errorCode,
            String message,
            List<String> details,
            HttpStatus status,
            WebRequest request
    ){
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;

        return handleExceptionInternal(
                ex,
                RestError
                        .builder()
                        .errorCode(errorCode)
                        .errorMessage(message)
                        .details(details)
                        .status(status.value())
                        .path(servletWebRequest.getRequest().getRequestURI())
                        .build(),
                new HttpHeaders(),
                status,
                request
        );

    }
}
