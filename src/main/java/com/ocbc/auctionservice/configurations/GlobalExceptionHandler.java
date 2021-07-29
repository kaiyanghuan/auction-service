package com.ocbc.auctionservice.configurations;

import com.ocbc.auctionservice.controllers.responses.ExceptionResponse;
import com.ocbc.auctionservice.exceptions.BusinessException;
import com.ocbc.auctionservice.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ExceptionResponse handleMethodArgumentNotValidExceptions(
            MethodArgumentNotValidException ex) {
        return ExceptionResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .errorMessage(Objects.requireNonNull(ex.getFieldError()).getDefaultMessage())
                .exceptionClass(ex.getClass().getCanonicalName())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ExceptionResponse handleBusinessExceptions(
            BusinessException ex) {
        return createExceptionResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ExceptionResponse handleNotFoundExceptions(
            NotFoundException ex) {
        return createExceptionResponse(ex, HttpStatus.NOT_FOUND);
    }

    private ExceptionResponse createExceptionResponse(Exception ex, HttpStatus status) {
        return ExceptionResponse.builder()
                .status(status)
                .errorMessage(ex.getLocalizedMessage())
                .exceptionClass(ex.getClass().getCanonicalName())
                .build();
    }
}
