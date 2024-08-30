// src/main/java/org/example/controller/LogoutController.java
package org.example.controller;

import org.example.service.TokenBlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogoutController extends BaseController {

    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader(value = "Authorization", required = false) String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Invalid or missing token.");
        }
        String jwtToken = token.substring(7); // Remove "Bearer " prefix
        tokenBlacklistService.invalidateToken(jwtToken);
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Logout successful.");
    }
}