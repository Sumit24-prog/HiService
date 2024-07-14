package org.example.service;

import org.example.model.ServiceRequest;
import org.example.repository.ServiceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceRequestService {

    private final ServiceRequestRepository serviceRequestRepository;

    @Autowired
    public ServiceRequestService(ServiceRequestRepository serviceRequestRepository) {
        this.serviceRequestRepository = serviceRequestRepository;
    }

    public ServiceRequest createServiceRequest(ServiceRequest serviceRequest) {
        return serviceRequestRepository.save(serviceRequest);
    }

    public Optional<ServiceRequest> getServiceRequestById(String id) {
        return serviceRequestRepository.findById(id);
    }

    public List<ServiceRequest> getAllServiceRequests() {
        return serviceRequestRepository.findAll();
    }

    public ServiceRequest updateServiceRequest(String id, ServiceRequest serviceRequest) {
        if (serviceRequestRepository.existsById(id)) {
            serviceRequest.setServiceId(id);
            return serviceRequestRepository.save(serviceRequest);
        }
        return null;
    }

    public void deleteServiceRequest(String id) {
        serviceRequestRepository.deleteById(id);
    }

    public List<ServiceRequest> getServiceRequestsByUserId(String userId) {
        return serviceRequestRepository.findByUserId(userId);
    }
}