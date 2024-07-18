package com.event.service;

import com.event.DTO.UserDTO;
import com.event.entity.Role;
import com.event.entity.User;
import com.event.exception.ResourceNotFoundException;
import com.event.repository.UserRepository;
import com.event.util.UserAuthenticationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "userCache")
@Slf4j
public class UserServiceImpl {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MessageSource messageSource;

    @Cacheable("users")
    public List<User> getAllUser() {
        doLongRunningTask();
        return userRepository.findAll();
    }

    @Cacheable(cacheNames = "user", key = "#userId", unless = "#result == null")
    public Optional<User> findById(Long userId, Locale locale) {
        doLongRunningTask();
        return Optional.ofNullable(userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException(messageSource.getMessage("user.not.found.msg", null, locale) + " " + userId)));
    }

    @Caching(evict = {@CacheEvict(cacheNames = "users", allEntries = true),
            @CacheEvict(cacheNames = "user", key = "#userId")})
    public String updateUser(Long userId, UserDTO userRequest, Locale locale) throws ResourceNotFoundException, BadRequestException {
        doLongRunningTask();
        User AuthenticateUser = UserAuthenticationUtil.getUserByAuthentication();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(messageSource.getMessage("user.not.found.msg", null, locale) + " " + userId));
        if (AuthenticateUser.getId() != userId) {
            throw new UsernameNotFoundException(messageSource.getMessage("user.not.access.err.msg", null, locale));
        }

        if (user.getEmail().equals(userRequest.getEmail()) || (!user.getEmail().equals(userRequest.getEmail()) && !userRepository.existsByEmail(userRequest.getEmail()))) {
            // update user
            var updatedUser = User.builder().id(userId).name(userRequest.getName()).email(userRequest.getEmail()).password(passwordEncoder.encode(userRequest.getPassword()))
                    .gender(userRequest.getGender()).address(userRequest.getAddress()).mobile(userRequest.getMobile())
                    .eventsOfInterest(userRequest.getEventsOfInterest()).role(Role.USER).build();

            Optional.ofNullable(userRepository.save(updatedUser))
                    .orElseThrow(() -> new ResourceNotFoundException(messageSource.getMessage("user.registration.unsuccessful.msg", null, locale)));
            return messageSource.getMessage("user.profile.update.msg", null, locale);
        } else {
            throw new BadRequestException(messageSource.getMessage("user.email.exist.msg", null, locale) + userRequest.getEmail() + messageSource.getMessage("user.change.email.msg", null, locale));
        }
    }

    @Caching(evict = {@CacheEvict(cacheNames = "user", key = "#userId"),
            @CacheEvict(cacheNames = "users", allEntries = true)})
    public void deleteUser(Long userId, Locale locale) {
        doLongRunningTask();
        User AuthenticateUser = UserAuthenticationUtil.getUserByAuthentication();
        if (AuthenticateUser.getId() != userId) {
            throw new UsernameNotFoundException(messageSource.getMessage("user.not.access.delete.user.msg", null, locale));
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(messageSource.getMessage("user.not.found.msg", null, locale) + " " + userId));
        userRepository.delete(user);
        log.info("Deleted the user with Id: " + user.getId());
    }

    private void doLongRunningTask() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}