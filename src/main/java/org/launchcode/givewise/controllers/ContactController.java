package org.launchcode.givewise.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.launchcode.givewise.dto.ContactFormDTO;
import org.launchcode.givewise.models.Product;
import org.launchcode.givewise.models.data.ProductRepository;
import org.launchcode.givewise.request.ProductRequest;
import org.launchcode.givewise.service.ContactService;
import org.launchcode.givewise.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.launchcode.givewise.config.SwaggerConfig.BASIC_AUTH_SECURITY_SCHEME;

@RestController
@RequestMapping("/api/contact/submit")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<?> submitContactForm(@RequestBody ContactFormDTO contactForm) {
        try {
            contactService.saveContactForm(contactForm);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to submit contact form");
        }
    }



    }



