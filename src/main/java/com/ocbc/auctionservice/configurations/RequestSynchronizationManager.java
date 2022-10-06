package com.ocbc.auctionservice.configurations;

import com.ocbc.auctionservice.authentications.JwtApiClient;
import com.ocbc.auctionservice.authentications.JwtInterface;
import com.ocbc.auctionservice.authentications.JwtUser;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Service;

@Service("localRequestSynchronizationManager")
public class RequestSynchronizationManager {

    private static final ThreadLocal<JwtInterface> jwtUser = new NamedThreadLocal<>("JwtUser");

    public void setCurrentRequestHeader(JwtInterface currentJwtUser){
        jwtUser.set(currentJwtUser);
    }

    public JwtInterface getJwtUser(){
        return jwtUser.get();
    }

    public String loggedInUsername(){
        JwtInterface principal = getJwtUser();
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
