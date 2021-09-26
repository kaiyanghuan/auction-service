package com.ocbc.auctionservice.controllers.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private Integer id;
    private String name;
    private String address;
    private int age;
}
