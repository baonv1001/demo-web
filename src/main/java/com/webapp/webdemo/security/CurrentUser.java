package com.webapp.webdemo.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.TYPE}) // Được sử dụng trên class, interface, method
@Retention(RetentionPolicy.RUNTIME) // Tồn tại trong lúc chạy chương trình
@Documented
@AuthenticationPrincipal
public @interface CurrentUser {
}
