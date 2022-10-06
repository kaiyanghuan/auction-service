package com.ocbc.auctionservice.controllers.requests;

import com.ocbc.auctionservice.utils.helpers.OnCreate;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserRequest {

    @NotNull(groups = OnCreate.class)
    @NotEmpty(message = "{userRequest.name.notEmpty}")
    private String name;

    @NotNull(groups = OnCreate.class, message = "{userRequest.address.notNull}")
    private String address;

    @NotNull(groups = OnCreate.class, message = "{userRequest.age.notNull}")
    @Min(value = 18, message = "{userRequest.age.min}")
    private Integer age;

    @NotNull(groups = OnCreate.class)
    private String password;

    @NotNull(groups = OnCreate.class)
    private String roles;

    @NotNull(groups = OnCreate.class)
    private String permissions;
}
