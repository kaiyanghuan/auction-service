package com.ocbc.auctionservice.authentications;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

import static java.lang.String.format;

@Data
@Builder
public class JwtApiClient implements JwtInterface {

    private String applicationName;
    private String applicationCode;
    private String description;
    private String applicationOwner;
    private String approver;
    private Date dateIssued;

    @Override
    public String toString() {
        return format("JwtUser(applicationName=%s, applicationCode=%s, applicationOwner=%, approver=%, dateIssued=%s)",
                applicationName, applicationCode, applicationOwner, approver, dateIssued);
    }
}
