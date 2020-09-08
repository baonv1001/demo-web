package com.webapp.webdemo.service;

import com.webapp.webdemo.payload.request.LoginRequest;
import com.webapp.webdemo.payload.request.SignUpRequest;
import com.webapp.webdemo.payload.response.ApiResponse;
import com.webapp.webdemo.payload.response.TokenResponse;

public interface UserService {
    TokenResponse login(LoginRequest loginRequest);
    ApiResponse registerUser(SignUpRequest signUpRequest);
}
