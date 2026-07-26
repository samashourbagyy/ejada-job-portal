package com.ejada.job_portal.controller;

import com.ejada.job_portal.dto.request.LoginRequest;
import com.ejada.job_portal.dto.request.RegisterRequest;
import com.ejada.job_portal.dto.response.LoginResponse;
import com.ejada.job_portal.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String register(
            @RequestBody RegisterRequest request
    ) {

        authService.register(request);

        return "User registered successfully";
    }

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request
    ) {

        return authService.login(request);
    }

    @PostMapping("/refresh")
    public LoginResponse refresh(
            @RequestParam String refreshToken
    ) {

        return authService.refreshToken(refreshToken);
    }

}