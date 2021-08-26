package com.ocbc.auctionservice.authentications;

import com.ocbc.auctionservice.entities.User;
import com.ocbc.auctionservice.entities.UserPrincipal;
import com.ocbc.auctionservice.exceptions.jwts.JwtExpiredException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    @Value("${app.auth.jwtSecret}")
    private String jwtSecret = "${JWT_SECRET:auction-service!0941240918358687214785814187872726}";

    @Value("${app.auth.jwtExpirationInMs}")
    private Long jwtExpiration;

    private SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

    public String generateToken(Authentication authentication){
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);
        Set<String> authorities = userPrincipal.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        UserPrincipal user = (UserPrincipal) userPrincipal;
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("roles", authorities)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key)
                .compact();
    }

    public String getUsernameFromJwt(String token){
        return getClaims(token).getSubject();
    }

    public Claims getClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Boolean validateToken(String token){
        Claims claims = getClaims(token);
        if (claims.getExpiration().before(claims.getIssuedAt())){
            throw new JwtExpiredException("Token has been expired");
        }
        return true;
    }
}
