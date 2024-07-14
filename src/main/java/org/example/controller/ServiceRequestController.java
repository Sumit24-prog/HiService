package org.example.controller;

import org.example.model.ServiceRequest;
import org.example.service.ServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/serviceRequests")
public class ServiceRequestController extends BaseController {

    private final ServiceRequestService serviceRequestService;

    @Autowired
    public ServiceRequestController(ServiceRequestService serviceRequestService) {
        this.serviceRequestService = serviceRequestService;
    }

    @PostMapping
    public ResponseEntity<?> createServiceRequest(@RequestBody ServiceRequest serviceRequest) {
        try {
            ServiceRequest createdServiceRequest = serviceRequestService.createServiceRequest(serviceRequest);
            if (createdServiceRequest != null) {
                return ResponseEntity.ok(createdServiceRequest);
            } else {
                return ResponseEntity.badRequest().body("Service Request could not be created.");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to create service request: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getServiceRequestById(@PathVariable String id) {
        return serviceRequestService.getServiceRequestById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ServiceRequest>> getAllServiceRequests() {


        List<ServiceRequest> serviceRequests = serviceRequestService.getAllServiceRequests();

        if (!serviceRequests.isEmpty()) {
            return ResponseEntity.ok(serviceRequests);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateServiceRequest(@PathVariable String id, @RequestBody ServiceRequest serviceRequest) {
        ServiceRequest updatedServiceRequest = serviceRequestService.updateServiceRequest(id, serviceRequest);
        if (updatedServiceRequest != null) {
            return ResponseEntity.ok(updatedServiceRequest);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteServiceRequest(@PathVariable String id) {
        boolean exists = serviceRequestService.getServiceRequestById(id).isPresent();
        if (exists) {
            serviceRequestService.deleteServiceRequest(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{userId}/serviceRequests")
    public ResponseEntity<List<ServiceRequest>> getServiceRequestsByUserId(@PathVariable String userId) {
        List<ServiceRequest> serviceRequests = serviceRequestService.getServiceRequestsByUserId(userId);
        if (!serviceRequests.isEmpty()) {
            return ResponseEntity.ok(serviceRequests);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}