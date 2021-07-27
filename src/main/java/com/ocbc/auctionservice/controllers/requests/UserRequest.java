package com.ocbc.auctionservice.controllers.requests;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UserRequest {

    @NotEmpty
    private String name;

    @NotNull
    private String address;

    @Pattern(regexp = "[0-9a-zA-Z@.]", message = "Please follow proper email address")
    private String email;

    @Min(value = 18, message = "Hey, must be more than 18")
    @Max(25)
    private int age;
}
