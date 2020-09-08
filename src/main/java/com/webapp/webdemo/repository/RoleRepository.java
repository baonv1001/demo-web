package com.webapp.webdemo.repository;

import com.webapp.webdemo.constants.enums.RoleName;
import com.webapp.webdemo.entities.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(RoleName roleName);

    @Query("SELECT ur.role.roleName FROM UserRole ur " +
            "WHERE ur.user.userNo = :userNo")
    List<RoleName> getRoleNames(@Param("userNo") Long userNo);
}
