package com.app.mmm.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.mmm.dto.ApiResponse;
import com.app.mmm.dto.RegisterDTO;
import com.app.mmm.dto.SignInDTO;
import com.app.mmm.dto.VerifyOtpDTO;
import com.app.mmm.security.JwtAuthResponse;
import com.app.mmm.service.AuthenticationService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
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

    @Operation(description = "Send OTP for Password Reset")
    @PostMapping("/forgotpassword")
    public ResponseEntity<?> forgotPassword(@RequestParam String email) {
        return ResponseEntity.ok(authenticationService.sendOtpForPasswordReset(email));
    }

    @Operation(description = "Verify OTP and Reset Password")
    @PostMapping("/verify-otp")
    public ResponseEntity<?> resetPassword(@Validated @RequestBody VerifyOtpDTO verifyOtpDTO) {
        return ResponseEntity.ok(authenticationService.verifyOtpAndResetPassword(verifyOtpDTO));
    }
    
    @Operation(description = "Admin Sign in")
    @PostMapping("/admin/signin")
    public ResponseEntity<?> adminSignIn(@Validated @RequestBody SignInDTO signInDTO) {
        String token = authenticationService.adminSignIn(signInDTO);
        JwtAuthResponse authResponse = new JwtAuthResponse();
        authResponse.setAccessToken(token);
        return ResponseEntity.status(HttpStatus.OK).body(authResponse);
    }
    
    @Operation(description = "Google Login Success")
    @GetMapping("/login")
    public String googleLoginSuccess(Principal principal) {

    	return "Hi " + principal.getName() + " Welcome to Oauth2";
        
    }


    @Operation(description = "Google Login Failure")
    @GetMapping("/google-login-failure")
    public ResponseEntity<?> googleLoginFailure() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ApiResponse("Google login failed"));
    }
}

