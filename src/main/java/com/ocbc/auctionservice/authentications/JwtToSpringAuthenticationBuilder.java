package com.ocbc.auctionservice.authentications;

import com.ocbc.auctionservice.entities.User;
import com.ocbc.auctionservice.services.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class JwtToSpringAuthenticationBuilder {

    private JwtTokenProvider jwtTokenProvider;
    private UserService userService;

    public JwtToSpringAuthenticationBuilder(JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    private TokenHolder tokenHolder;
    private HttpServletRequest request;

    public JwtToSpringAuthenticationBuilder withToken(TokenHolder tokenHolder) {
        this.tokenHolder = tokenHolder;
        return this;
    }

    public JwtToSpringAuthenticationBuilder withHttpServletRequest(HttpServletRequest request) {
        this.request = request;
        return this;
    }

    public Authentication build(){
        Claims claims = jwtTokenProvider.getClaims(tokenHolder.getToken());
        String username = claims.getSubject();
        List<String> roles = (List<String>) claims.get("roles");
        List<SimpleGrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        JwtInterface jwt = createAuthenticatedDetailsFor(username, claims);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(jwt, null, authorities);
        WebAuthenticationDetailsSource webAuthenticationDetailsSource = new WebAuthenticationDetailsSource();
        authenticationToken.setDetails(webAuthenticationDetailsSource.buildDetails(request));

        return authenticationToken;
    }

    private JwtInterface createAuthenticatedDetailsFor(String name, Claims claims){
        switch(tokenHolder.getTokenType()){
            case USER:
                User user = userService.getUser(name);
                return JwtUser.builder()
                        .username(user.getUsername())
                        .name(user.getName())
                        .roles((List<String>) claims.get("roles"))
                        .dateIssued(claims.getIssuedAt())
                        .build();
            case API_CLIENT:
                //TODO: Update later when we have API Client key
                return null;
            default:
                return null;
        }
    }
}
