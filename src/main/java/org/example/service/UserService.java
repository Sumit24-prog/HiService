package org.example.service;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerNewUser(User user) {
        user.setUserId(generateUserId());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    private String generateUserId() {
        Random random = new Random();
        int number = random.nextInt(9000000) + 1000000; // Ensure it's always 7 digits
        return String.valueOf(number);
    }

    // Add this method
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}