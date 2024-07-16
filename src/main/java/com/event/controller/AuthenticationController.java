package com.event.controller;

import com.event.DTO.SigninRequest;
import com.event.DTO.UserDTO;
import com.event.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/registration")
    public ResponseEntity<Object> registration(@Valid @RequestBody UserDTO request,@RequestHeader(name="Accept-Language", required= false) Locale locale) {
        log.info("Processing user registration {}", request);
        try {
            return ResponseEntity.ok(authenticationService.registration(request, locale));
        } catch (Exception e) {
            log.error("Error processing user registration for request: {}. Error: {}", request, e.getMessage(), e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody SigninRequest request, @RequestHeader(name="Accept-Language", required= false) Locale locale) {
        try {
            return ResponseEntity.ok(authenticationService.login(request, locale));
        } catch (Exception e) {
            log.error("Error processing user login for request: {}. Error: {}", request, e.getMessage(), e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}