package com.event.controller;

import com.event.DTO.UserDTO;
import com.event.entity.User;
import com.event.exception.ResourceNotFoundException;
import com.event.repository.UserRepository;
import com.event.service.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = userService.getAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long userId, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        try {
            log.info("Fetching user with ID {}", userId);
            Optional<User> Optionaluser = userService.findById(userId, locale);
            User user = Optionaluser.orElse(new User());
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            log.error("User with ID {} not found ", userId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageSource.getMessage("user.not.found.msg", null, locale) + " " + userId);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long userId, @Valid @RequestBody UserDTO userDetails, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        try {
            String updatedUser = userService.updateUser(userId, userDetails, locale);
            log.info("Updated user with ID {}", userId);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            log.error("User with ID {} not found for update", userId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageSource.getMessage("user.not.found.msg", null, locale) + " " + userId);
        } catch (BadRequestException ex) {
            log.error("User email is already exist with another user " + userDetails.getEmail());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageSource.getMessage("user.email.exist.msg", null, locale) + userDetails.getEmail() + messageSource.getMessage("user.change.email.msg", null, locale));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        log.info("Deleting user with ID {}", userId);
        try {
            userService.deleteUser(userId, locale);
            log.info("Deleted user with ID {}", userId);
            return ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage("user.deleted.successfully.msg", null, locale) + " " + userId);
        } catch (ResourceNotFoundException ex) {
            log.error("User with ID {} not found for deletion", userId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(messageSource.getMessage("user.not.access.delete.user.msg", null, locale) + "" + userId);
        }
    }
}