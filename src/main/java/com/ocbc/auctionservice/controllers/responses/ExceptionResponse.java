package com.ocbc.auctionservice.controllers.responses;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ExceptionResponse {
    private HttpStatus status;
    private String errorMessage;
    private String exceptionClass;
}
