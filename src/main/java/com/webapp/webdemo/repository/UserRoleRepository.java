package com.webapp.webdemo.repository;

import com.webapp.webdemo.entities.security.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
