package com.webapp.webdemo.service.impl;

import com.webapp.webdemo.entities.security.Role;
import com.webapp.webdemo.entities.security.User;
import com.webapp.webdemo.entities.security.UserRole;
import com.webapp.webdemo.exception.AppException;
import com.webapp.webdemo.payload.request.LoginRequest;
import com.webapp.webdemo.payload.request.SignUpRequest;
import com.webapp.webdemo.payload.response.ApiResponse;
import com.webapp.webdemo.payload.response.TokenResponse;
import com.webapp.webdemo.repository.RoleRepository;
import com.webapp.webdemo.repository.UserRepository;
import com.webapp.webdemo.repository.UserRoleRepository;
import com.webapp.webdemo.security.JwtTokenProvider;
import com.webapp.webdemo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    // Repository
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    @Transactional
    @Override
    public TokenResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        return new TokenResponse(token);
    }

    @Override
    @Transactional
    public ApiResponse registerUser(SignUpRequest signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ApiResponse(false, "Username is already taken!");
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ApiResponse(false, "Email Address already in use!");
        }

        // Creating user's account
        User user = User.builder()
                .name(signUpRequest.getName())
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .build();
        User newUser = userRepository.save(user);

        Role role = roleRepository.findByRoleName(signUpRequest.getRoleName())
                .orElseThrow(() -> new AppException("User Role not set."));

        UserRole userRole = new UserRole();
        userRole.setUser(newUser);
        userRole.setRole(role);
        userRoleRepository.save(userRole);

        return new ApiResponse(true, "User registered successfully");
    }
}
