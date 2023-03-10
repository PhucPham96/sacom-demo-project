package com.example.senderservice.configuration;

import com.example.senderservice.common.FunctionCommonUtils;
import com.example.senderservice.configuration.exception.CommonException;
import com.example.senderservice.configuration.exception.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FunctionCommonUtils functionCommonUtils;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> mainExceptionHandler(Exception e, HandlerMethod handlerMethod, HttpServletRequest request) {
        logger.info("mainExceptionHandler\n", e);
        ErrorResponse response = ErrorResponse.builder()
                .timestamp(functionCommonUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"))
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ErrorResponse> mainExceptionHandler(CommonException e, HandlerMethod handlerMethod, HttpServletRequest request) {
        logger.info("CommonException\n", e);
        ErrorResponse response = ErrorResponse.builder()
                .timestamp(functionCommonUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"))
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

}
