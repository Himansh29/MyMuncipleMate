package com.app.mmm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.mmm.dto.ApiResponse;
import com.app.mmm.dto.ForgotPasswordDTO;
import com.app.mmm.dto.RegisterDTO;
import com.app.mmm.dto.SignInDTO;
import com.app.mmm.security.JwtAuthResponse;
import com.app.mmm.service.AuthenticationService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Operation(description = "Sign in")
    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@Validated @RequestBody SignInDTO signInDTO) {
        String token = authenticationService.signIn(signInDTO);
        JwtAuthResponse authResponse = new JwtAuthResponse();
        authResponse.setAccessToken(token);
        return ResponseEntity.status(HttpStatus.OK).body(authResponse);
    }

    @Operation(description = "Sign up")
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Validated @RequestBody RegisterDTO signupdto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.register(signupdto));
    }

    @Operation(description = "Forgot password")
    @PostMapping("/forgotpassword")
    public ResponseEntity<?> forgotPassword(@Validated @RequestBody ForgotPasswordDTO forgotPasswordDTO) {
        return ResponseEntity.ok(authenticationService.forgotPassword(forgotPasswordDTO));
    }
}

