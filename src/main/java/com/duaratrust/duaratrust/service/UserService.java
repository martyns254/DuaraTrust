package com.duaratrust.duaratrust.service;

import com.duaratrust.duaratrust.dto.LoginRequest;
import com.duaratrust.duaratrust.dto.LoginResponse;
import com.duaratrust.duaratrust.dto.RegisterRequest;
import com.duaratrust.duaratrust.dto.UserResponse;
import com.duaratrust.duaratrust.entity.User;
import com.duaratrust.duaratrust.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.duaratrust.duaratrust.config.JwtUtil;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public UserResponse register(RegisterRequest request) {
        if (userRepository.findByPhoneNumber(request.getPhoneNumber()) != null) {
            throw new IllegalArgumentException("Phone number already registered");
        }

        User user = new User();
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setRole("MEMBER");
        user.setCreatedAt(LocalDateTime.now());

        User saved = userRepository.save(user);

        return new UserResponse(saved.getId(), saved.getPhoneNumber(), saved.getRole());
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByPhoneNumber(request.getPhoneNumber());

        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new BadCredentialsException("Invalid phone number or password");
        }

        String token = jwtUtil.generateToken(user.getPhoneNumber());

        return new LoginResponse(token, user.getPhoneNumber(), user.getRole());
    }
}
