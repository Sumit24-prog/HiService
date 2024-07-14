package org.example.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.model.ServiceRequest;
import org.example.model.User;
import java.util.List;

@Getter
@Setter
public class HomeResponseDTO {
    private User user;
    private List<ServiceRequest> serviceRequests;

    public HomeResponseDTO(User user, List<ServiceRequest> serviceRequests) {
        this.user = user;
        this.serviceRequests = serviceRequests;
    }

    // Getters and Setters
}