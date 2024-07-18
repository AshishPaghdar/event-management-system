package com.event.repository;

import com.event.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String username);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
//    User findById(Long userId);
}

