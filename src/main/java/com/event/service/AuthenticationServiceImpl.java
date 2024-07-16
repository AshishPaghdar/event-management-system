package com.event.service;

import com.event.DTO.JwtAuthenticationResponse;
import com.event.DTO.SigninRequest;
import com.event.DTO.UserDTO;
import com.event.entity.Role;
import com.event.entity.User;
import com.event.exception.ResourceNotFoundException;
import com.event.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.cglib.core.Local;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MessageSource messageSource;

    @Caching(evict = {@CacheEvict(cacheNames = "users", allEntries = true)})
    @Override
    public String registration(UserDTO request, Locale locale) throws BadRequestException {
        if(userRepository.existsByEmail(request.getEmail())){
            throw new BadRequestException(messageSource.getMessage("user.email.exist.msg", null, locale) + " " + request.getEmail() + messageSource.getMessage("user.change.email.msg", null, locale));
        }
        var user = User.builder().name(request.getName()).email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .gender(request.getGender()).address(request.getAddress()).mobile(request.getMobile())
                .eventsOfInterest(request.getEventsOfInterest()).role(Role.USER).build();

        Optional.ofNullable(userRepository.save(user))
                .orElseThrow(() -> new ResourceNotFoundException(messageSource.getMessage("user.registration.unsuccessful.msg", null, locale)));
        return messageSource.getMessage("user.registration.successful.msg", null, locale);
    }

    @Override
    public JwtAuthenticationResponse login(SigninRequest request, Locale locale) {
         authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException(messageSource.getMessage("user.invalid.email.msg", null, locale)));
        Long userId = Long.parseLong(request.getId());
        if(user.getId()!=userId || !user.getRole().name().equals(request.getRole())){
            throw new IllegalArgumentException(messageSource.getMessage("user.invalid.credential.msg", null, locale));
        }
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}