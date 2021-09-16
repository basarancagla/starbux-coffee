package com.example.starbuxcoffeeassignment.controller;

import com.example.starbuxcoffeeassignment.authentication.TokenManager;
import com.example.starbuxcoffeeassignment.model.AuthenticationRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@Api( tags = "Login")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenManager tokenManager;

    @ApiOperation(value = "Login")
    @PostMapping
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(),authenticationRequest.getPassword()));

            return ResponseEntity.ok(tokenManager.generateToken(authenticationRequest.getUserName()));
        } catch (BadCredentialsException e){
            throw new Exception("Incorrect password or username",e);
        }
    }
}
