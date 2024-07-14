package org.example.service;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        User user;
        if (identifier.contains("@")) {
            user = userRepository.findByEmail(identifier)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + identifier));
        } else {
            user = userRepository.findByUserId(identifier)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + identifier));
        }

        return new CustomUserDetails(user.getUserId(), user.getUsername(), user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}