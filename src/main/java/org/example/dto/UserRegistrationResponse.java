// src/main/java/org/example/dto/UserRegistrationResponse.java
package org.example.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegistrationResponse {
    private String customerId;
    private String userEmail;
    private String message;

    public UserRegistrationResponse(String customerId, String userEmail, String message) {
        this.customerId = customerId;
        this.userEmail = userEmail;
        this.message = message;
    }

}