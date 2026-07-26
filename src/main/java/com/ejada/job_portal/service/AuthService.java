package com.ejada.job_portal.service;

import com.ejada.job_portal.dto.request.LoginRequest;
import com.ejada.job_portal.dto.request.RegisterRequest;
import com.ejada.job_portal.dto.response.LoginResponse;
import com.ejada.job_portal.entity.User;
import com.ejada.job_portal.exception.DuplicateResourceException;
import com.ejada.job_portal.repository.UserRepository;
import com.ejada.job_portal.security.CustomUserDetails;
import com.ejada.job_portal.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public void register(RegisterRequest request) {

        System.out.println("Step 1");

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Email already exists");
        }

        System.out.println("Step 2");

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("USER")
                .build();

        System.out.println("Step 3");

        userRepository.save(user);

        System.out.println("Step 4");
    }

    public LoginResponse login(LoginRequest request) {

        // Authenticate the user's credentials
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Load the user from the database
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        CustomUserDetails userDetails = new CustomUserDetails(user);

        // Generate JWTs
        String accessToken = jwtService.generateAccessToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);

        return new LoginResponse(accessToken, refreshToken);
    }

    public LoginResponse refreshToken(String refreshToken) {

        String email = jwtService.extractUsername(refreshToken);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        CustomUserDetails userDetails = new CustomUserDetails(user);

        // Validate the refresh token
        if (!jwtService.isTokenValid(refreshToken, userDetails)) {
            throw new RuntimeException("Invalid refresh token");
        }

        // Generate a new access token
        String newAccessToken = jwtService.generateAccessToken(userDetails);

        return new LoginResponse(
                newAccessToken,
                refreshToken
        );
    }
}