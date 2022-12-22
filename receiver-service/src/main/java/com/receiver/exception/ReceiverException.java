package com.receiver.exception;

import core.exception.ServiceException;
import core.response.ErrorData;
import core.response.ResponseStatus;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.stream.Collectors;

public class ReceiverException extends ServiceException {
    public ReceiverException(HttpStatus statusCode, Throwable cause, Object error, String errCode, String errMessage, ErrorData errorMapping, Object... args) {
        super(statusCode, cause, error, errCode, errMessage, errorMapping, args);
    }

    public ReceiverException(HttpStatus statusCode, Throwable cause, Object error, String errCode, String errMessage, Object... args) {
        super(statusCode, cause, error, errCode, errMessage, args);
    }
    public ReceiverException(HttpStatus statusCode, ResponseStatus status, ErrorData errorMapping, Object... args) {
        super(statusCode, null, null, status.getCode(), status.getMessage(), errorMapping, args);
    }


    public ReceiverException(HttpStatus statusCode, ErrorData errorData) {
        super(statusCode, errorData);
    }

    public ReceiverException(Set<? extends ConstraintViolation<?>> violations) {
        super(HttpStatus.BAD_REQUEST,
                null,
                null,
                ResponseStatus.INVALID_REQUEST_PARAMETER.getCode(),
                violations != null ? ResponseStatus.INVALID_REQUEST_PARAMETER.getMessage() + renderText(violations) : null);
    }

    private static String renderText(Set<? extends ConstraintViolation<?>> violations) {
        var text = violations.stream()
                .map(v -> v == null ? "null" : String.format("'%s' %s", convertSnakeCase(v.getPropertyPath().toString()), v.getMessage()) + " |")
                .collect(Collectors.joining(" "));

        return text.substring(0, text.length() - 2);
    }

    private static String convertSnakeCase(String str) {
        return str.replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
    }
}
