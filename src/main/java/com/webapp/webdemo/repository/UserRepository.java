package com.webapp.webdemo.repository;

import com.webapp.webdemo.entities.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User> findByUsername(String userName);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
