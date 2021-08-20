package com.ocbc.auctionservice.authentications;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

import static java.lang.String.format;

@Data
@AllArgsConstructor
public class JwtUser {

    private String username;
    private String name;
    private List<String> roles;
    private Date dateIssued;

    @Override
    public String toString(){
        return format("JwtUser(username=%s, name=%s, dateIssued=%s)", username, name, dateIssued);
    }
}
