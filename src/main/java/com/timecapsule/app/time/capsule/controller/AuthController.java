package com.timecapsule.app.time.capsule.controller;

import com.timecapsule.app.time.capsule.dto.SignupRequest;
import com.timecapsule.app.time.capsule.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private PasswordEncoder encoder; // declare the encoder field

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest){
       authService.signup(signupRequest);
       return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }

    @GetMapping("accountVerification/{token}")
    public ResponseEntity<?> verifyAccount(@PathVariable String token){
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account Activated Successully", HttpStatus.OK);
    }

}
