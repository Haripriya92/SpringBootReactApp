package org.launchcode.givewise.controllers;

import lombok.extern.slf4j.Slf4j;
import org.launchcode.givewise.models.User;
import org.launchcode.givewise.models.data.UserRepository;
import org.launchcode.givewise.request.UserRequest;
import org.launchcode.givewise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRequest userDto) {
        if (userRepo.existsByEmail(userDto.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }
        User user = userService.registerUser(userDto);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create user");
        }
        log.info("User created: {}", user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }

}
