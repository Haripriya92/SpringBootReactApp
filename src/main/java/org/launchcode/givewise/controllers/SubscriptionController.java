package org.launchcode.givewise.controllers;

import org.launchcode.givewise.models.Subscription;
import org.launchcode.givewise.models.data.SubscriptionRepository;
import org.launchcode.givewise.request.SubscriptionRequest;
import org.launchcode.givewise.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @PostMapping("/validate")
    public ResponseEntity<String> validateAndSubscribe(@RequestBody SubscriptionRequest subDto) {

        Subscription subscription = subscriptionRepository.findByEmail(subDto.getEmail());

        if (subscription != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }
      /* boolean isValid = subscriptionService.validateAndSubscribe(subDto.getEmail());

        if (isValid) {
             return ResponseEntity.status(HttpStatus.CREATED).body("Subscribed successfully");
        } */else {
            return ResponseEntity.status(900).body("Invalid email address.");
        }
    }
}
