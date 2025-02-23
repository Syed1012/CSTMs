package com.example.CSTMs.user.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.CSTMs.user.model.User;
import com.example.CSTMs.user.repository.UserRepository;
import com.example.CSTMs.user.security.JwtUtil;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // Register a new User
    public User registerUser(User user) {

        // Check if user exists by Email
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User with email " + user.getEmail() + " already exists");
        }

        // Hash the Password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRole(2); // Default role is 2 (User)

        return userRepository.save(user);
    }

    // Authenticate User & Generate JWT Token When Login
    public String authenticateUser(String email, String rawPassword) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found!");
        }

        User user = userOptional.get();
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials!");
        }

        // Generate JWT Token
        return jwtUtil.generateToken(user);
    }
}
