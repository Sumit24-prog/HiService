package org.example.repository;

import org.example.model.ServiceRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ServiceRequestRepository extends MongoRepository<ServiceRequest, String> {

    List<ServiceRequest> findByUserId(String userId);
}