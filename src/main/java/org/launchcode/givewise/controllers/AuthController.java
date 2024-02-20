package org.launchcode.givewise.controllers;


import org.launchcode.givewise.models.User;
import org.launchcode.givewise.models.data.UserRepository;
import org.launchcode.givewise.request.AuthResponse;
import org.launchcode.givewise.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepo;
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        User user = userRepo.findByEmailIgnoreCase(loginRequest.getEmail());
        if (user!=null) {
            if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                return ResponseEntity.ok(new AuthResponse(user.getId(), user.getUserName(), user.getRole().getUserRole()));
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}