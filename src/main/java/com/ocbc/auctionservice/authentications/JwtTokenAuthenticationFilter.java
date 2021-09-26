package com.ocbc.auctionservice.authentications;

import com.ocbc.auctionservice.services.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.SneakyThrows;
import org.apache.tomcat.util.http.parser.Authorization;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private String path = "api/v1/auth/login";
    private final String API_TOKEN_HEADER = "x-api-key";
    private RequestMatcher requestMatcher = new AntPathRequestMatcher(path);

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
    ) throws ServletException, IOException {

        try {
            if (hasNoAuthorization(request)) {
                filterChain.doFilter(request, response);
                return;
            }

            TokenHolder tokenHolder = generateTokenHolder(request);
            checkAuthenticationAndValidity(tokenHolder.getToken());

            Authentication authentication = new JwtToSpringAuthenticationBuilder(jwtTokenProvider, userService)
                    .withToken(tokenHolder)
                    .withHttpServletRequest(request)
                    .build();

            SecurityContextHolder.getContext().setAuthentication(authentication);
            MDC.put("username", UserContext.loggedInUsername());
            filterChain.doFilter(request, response);
        } catch(Exception e){
            handleException(e, response);
        } finally {
            SecurityContextHolder.clearContext();
        }

    }

    private Boolean checkAuthenticationAndValidity(String token){
        return jwtTokenProvider.validateToken(token);
    }

    private TokenHolder generateTokenHolder(HttpServletRequest request){
        if(hasAuthHeader(request, AUTHORIZATION)){
            String token = extractAndDecodeJwtTokenFrom(request, AUTHORIZATION);
            return TokenHolder.builder()
                    .token(token)
                    .tokenType(TokenHolder.TokenType.USER)
                    .build();
        }
        String token = extractAndDecodeJwtTokenFrom(request, API_TOKEN_HEADER);
        return TokenHolder.builder()
                .token(token)
                .tokenType(TokenHolder.TokenType.API_CLIENT)
                .build();
    }

    private String extractAndDecodeJwtTokenFrom(HttpServletRequest request, String headerKey){
        String header = request.getHeader(headerKey);
        return header.substring("Bearer ".length());
    }

    private Boolean hasNoAuthorization(HttpServletRequest request){
        return !(hasAuthHeader(request, AUTHORIZATION) || hasAuthHeader(request, API_TOKEN_HEADER));
    }

    private Boolean hasAuthHeader(HttpServletRequest request, String headerKey){
        String header = request.getHeader(headerKey);
        return header != null && header.startsWith("Bearer ");
    }

    private void handleException(Exception e, HttpServletResponse response) throws Exception{
        if (e instanceof ExpiredJwtException){
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Token is not valid anymore");
        } else if (e instanceof UnsupportedJwtException){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unsupported Jwt Format");
        } else if (e instanceof MalformedJwtException){
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Token is malformed");
        }
        throw e;
    }
}
