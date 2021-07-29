package com.ocbc.auctionservice.controllers.requests;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserRequest {

    @NotEmpty(message = "{userRequest.name.notEmpty}")
    private String name;

    @NotNull(message = "{userRequest.address.notNull}")
    private String address;

    @Min(value = 18, message = "{userRequest.age.min}")
    @NotNull(message = "{userRequest.age.notNull}")
    private Integer age;
}
