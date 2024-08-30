package org.example.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Document
public class ServiceRequest {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private String serviceId;

    private String userId;
    private String serviceType; // e.g., "AC Repair", "Appliance Repair"
    private String description;
    private String status; // e.g., "Pending", "In Progress", "Completed"
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateSlot; // For date and time slot
    private String vendorName; // Name of the ven
    private BigDecimal price; // Price decided by the vendor
    private String address; // Address for the service
}