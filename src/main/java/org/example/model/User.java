package org.example.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
@Data
public class User {
    @Id
    private String userId; // Used as a primary key

    private String username;
    private String email; // Used as a business identifier
    private String password;
    private String address;
    private String contactNumber;
}