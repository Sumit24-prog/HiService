package org.example.controller;

import org.example.dto.HomeResponseDTO;
import org.example.model.ServiceRequest;
import org.example.model.User;
import org.example.service.ServiceRequestService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController extends BaseController {

    private final UserService userService;
    private final ServiceRequestService serviceRequestService;

    @Autowired
    public HomeController(UserService userService, ServiceRequestService serviceRequestService) {
        this.userService = userService;
        this.serviceRequestService = serviceRequestService;
    }

    @GetMapping("/home")
    public ResponseEntity<HomeResponseDTO> getHomePageContent() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username).orElse(null);
        if (user != null) {
            List<ServiceRequest> serviceRequests = serviceRequestService.getServiceRequestsByUserId(user.getUserId());
            HomeResponseDTO response = new HomeResponseDTO(user, serviceRequests);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}