package app.techify.controller;

import app.techify.dto.AuthResponse;
import app.techify.dto.LoginRequest;
import app.techify.dto.RefreshTokenRequest;
import app.techify.entity.Account;
import app.techify.repository.AccountRepository;
import app.techify.service.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AccountRepository accountRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("Login attempt for email: " + loginRequest.getEmail());
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            System.out.println("Authentication successful for user: " + userDetails.getUsername());

            Account account = accountRepository.findByEmail(userDetails.getUsername()).orElseThrow();

            String jwt = jwtService.generateToken(account.getId(), account.getEmail(), account.getRole(), account.getStatus());
            String refreshToken = jwtService.generateRefreshToken(account.getId(), account.getEmail(), account.getRole(), account.getStatus());

            account.setRefreshToken(refreshToken);
            accountRepository.save(account);

            return ResponseEntity.ok(new AuthResponse(jwt, refreshToken));
        } catch (Exception e) {
            System.out.println("Authentication failed: " + e.getMessage());
            return ResponseEntity.badRequest().body("Authentication failed: " + e.getMessage());
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request) {
        String refreshToken = request.getRefreshToken();
        String email = jwtService.extractEmail(refreshToken);
        Account account = accountRepository.findByEmail(email).orElseThrow();

        if (refreshToken.equals(account.getRefreshToken()) && !jwtService.isTokenExpired(refreshToken)) {
            String newToken = jwtService.generateToken(account.getId(), account.getEmail(), account.getRole(), account.getStatus());
            return ResponseEntity.ok(new AuthResponse(newToken, refreshToken));
        }

        return ResponseEntity.badRequest().body("Invalid refresh token");
    }

    @GetMapping("/protected")
    public ResponseEntity<String> protectedResource() {
        return ResponseEntity.ok("Access granted to protected resource");
    }
}