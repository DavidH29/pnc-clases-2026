package com.pnc.gamestore.services;

import com.pnc.gamestore.dto.request.LoginRequest;
import com.pnc.gamestore.dto.request.RegisterRequest;
import com.pnc.gamestore.dto.response.AuthResponse;
import com.pnc.gamestore.exception.BusinessRuleException;
import com.pnc.gamestore.exception.DuplicateEntityException;
import com.pnc.gamestore.model.User;
import com.pnc.gamestore.repositories.UserRepository;
import com.pnc.gamestore.util.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateEntityException("User", "username", request.getUsername());
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEntityException("User", "email", request.getEmail());
        }

        User user = new User(
                request.getUsername(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword())
        );

        userRepository.save(user);
        String token = jwtUtil.generateToken(user);
        return new AuthResponse(token, user.getUsername());
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new BusinessRuleException("Invalid username or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BusinessRuleException("Invalid username or password");
        }

        String token = jwtUtil.generateToken(user);
        return new AuthResponse(token, user.getUsername());
    }
}
