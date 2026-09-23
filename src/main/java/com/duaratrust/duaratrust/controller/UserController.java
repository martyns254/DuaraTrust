package com.duaratrust.duaratrust.controller;

import com.duaratrust.duaratrust.dto.LoginRequest;
import com.duaratrust.duaratrust.dto.LoginResponse;
import com.duaratrust.duaratrust.dto.RegisterRequest;
import com.duaratrust.duaratrust.dto.UserResponse;
import com.duaratrust.duaratrust.entity.User;
import com.duaratrust.duaratrust.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public String me() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "Authenticated as: " + auth.getName();
    }

    @PostMapping("/register")
    public UserResponse register(@Valid @RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return userService.login(request);
    }

}
