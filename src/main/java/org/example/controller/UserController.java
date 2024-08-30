package org.example.controller;

import org.example.dto.UserRegistrationResponse;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerNewUser(user);
        UserRegistrationResponse response = new UserRegistrationResponse(
                registeredUser.getUserId(),
                registeredUser.getEmail(),
                "Registration successful."
        );
        return ResponseEntity.ok(response);
    }

}