package com.ocbc.auctionservice.authentications;

import com.ocbc.auctionservice.exceptions.jwts.UnknownAuthenticationPrincipalException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Date;

import static java.lang.String.format;

public class UserContext {

    public static JwtInterface loggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof JwtUser) {
                return (JwtUser) authentication.getPrincipal();
            }
            if (principal instanceof JwtApiClient) {
                return (JwtApiClient) authentication.getPrincipal();
            }
            throw new UnknownAuthenticationPrincipalException(format("Trying to obtain JwtUser or JwtApiClient but Authentication Principal is %s",
                    principal.getClass().getName()));
        }
        return JwtUser.builder()
                .username("SYSTEM")
                .name("SYSTEM")
                .dateIssued(new Date())
                .roles(new ArrayList<>())
                .build();
    }

    public static String loggedInUsername() throws UnknownAuthenticationPrincipalException {
        JwtInterface principal = loggedInUser();
        if (principal instanceof JwtUser) {
            JwtUser jwtUser = (JwtUser) principal;
            return jwtUser.getUsername();
        }
        if (principal instanceof JwtApiClient) {
            JwtApiClient jwtApiClient = (JwtApiClient) principal;
            return jwtApiClient.getApplicationName();
        }
        return "No Logged User / App";
    }
}
