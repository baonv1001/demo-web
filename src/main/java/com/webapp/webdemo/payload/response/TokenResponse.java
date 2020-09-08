package com.webapp.webdemo.payload.response;

import com.webapp.webdemo.constants.CommonConstants;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class TokenResponse {
    private final String accessToken;
    private String tokenType = CommonConstants.HeaderConstants.BEARER_TOKEN;
}
