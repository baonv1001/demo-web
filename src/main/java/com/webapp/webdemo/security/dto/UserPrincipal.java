package com.webapp.webdemo.security.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webapp.webdemo.constants.enums.RoleName;
import com.webapp.webdemo.entities.security.User;
import com.webapp.webdemo.repository.RoleRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class UserPrincipal implements UserDetails {
    private static final long serialVersionUID = -8750837259331357927L;
    private Long userNo;
    private String name;
    private String username;

    @JsonIgnore
    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public static UserPrincipal create(User user, RoleRepository roleRepository){
        List<RoleName> roleNames = roleRepository.getRoleNames(user.getUserNo());
        List<GrantedAuthority> authorities = roleNames.stream()
                .map(roleName -> new SimpleGrantedAuthority(roleName.name()))
                .collect(Collectors.toList());

        return UserPrincipal.builder()
                .userNo(user.getUserNo())
                .name(user.getName())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .authorities(authorities)
                .build();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
