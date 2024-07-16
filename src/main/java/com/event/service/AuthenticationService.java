package com.event.service;

import com.event.DTO.JwtAuthenticationResponse;
import com.event.DTO.SigninRequest;
import com.event.DTO.UserDTO;
import org.apache.coyote.BadRequestException;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public interface AuthenticationService {
    String registration(UserDTO userDTO, Locale locale) throws BadRequestException;

    JwtAuthenticationResponse login(SigninRequest request,Locale locale);
}