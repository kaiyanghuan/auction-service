package com.ocbc.auctionservice.controllers;


import com.ocbc.auctionservice.authentications.JwtTokenProvider;
import com.ocbc.auctionservice.controllers.requests.LoginRequest;
import com.ocbc.auctionservice.controllers.responses.ApiReponse;
import com.ocbc.auctionservice.controllers.responses.JwtAuthenticationResponse;
import com.ocbc.auctionservice.entities.User;
import com.ocbc.auctionservice.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest){
        if(loginRequest.getUsername().isEmpty() || loginRequest.getPassword().isEmpty()){
            return new ResponseEntity<>(new ApiReponse(false, "Invalid username or password"), HttpStatus.BAD_REQUEST);
        }
        log.info("");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword())
        );

        String jwtToken = jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwtToken));
    }
}
