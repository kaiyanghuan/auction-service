package com.ocbc.auctionservice.controllers.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
@AllArgsConstructor
public class AppPageRequest {
    private int page;
    private int size;
    private Sort.Direction direction;
    private String field;
    private int total;
}
