package com.crack.taskNest.services;

import com.crack.taskNest.entity.User;
import com.crack.taskNest.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Check if email exists
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    // Check if username exists
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    // Register user after encoding password
    public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encode password
        userRepository.save(user);  // Save the user to the database
    }
}
