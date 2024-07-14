package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // This annotation denotes a configuration class that declares one or more @Bean methods and also triggers auto-configuration and component scanning.
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args); // This line starts the whole Spring framework.
    }
}