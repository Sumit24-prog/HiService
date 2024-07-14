# System Management System

This project is designed to manage various system operations, focusing on user and vendor interactions. It allows vendors to register, add services, and manage service requests, while users can request services and track their status. The backend is built with Spring Boot, leveraging MongoDB for data persistence.

## Installation

To set up this project locally, follow these steps:

1. Clone the repository:
```bash
git clone <repository-url>
```

2. Navigate to the project directory:
```bash
cd SystemManagementSystem
```

3. Install dependencies:
```bash
mvn install
```

4. Configure MongoDB:
- Ensure MongoDB is installed and running on your system.
- Update `src/main/resources/application.properties` with your MongoDB connection details:
```properties
spring.data.mongodb.uri=mongodb://localhost:27017/system_management
```

5. Run the application:
```bash
mvn spring-boot:run
```
## Project Description

This project serves as the backend for a comprehensive service management system, designed to facilitate a wide range
of service requests, including but not limited to AC repair, appliance repair, fridge, washing machine, 
and various other types of services. It acts as a server that accepts API requests and provides outputs 
accordingly, catering to both users who need services and vendors who provide them. 
The backend is responsible for handling user registrations, service requests, vendor management, 
and more, ensuring a seamless operation of the service management ecosystem. A frontend will be developed in 
the future to complement this backend, providing a user-friendly interface for interacting with the system.

## File Structure

The project is organized as follows:

```
SystemManagementSystem/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/
│   │   │       └── example/
│   │   │           ├── config/                # Configuration classes (e.g., SecurityConfig)
│   │   │           ├── controller/            # REST API Controllers
│   │   │           │   ├── AuthController.java # Handles authentication
│   │   │           │   └── ...                # Other controller files
│   │   │           ├── model/                 # Entity and DTO classes
│   │   │           │   ├── User.java          # User entity
│   │   │           │   └── ...                # Other model files
│   │   │           ├── repository/            # Spring Data JPA Repositories
│   │   │           │   ├── UserRepository.java# User repository
│   │   │           │   └── ...                # Other repository files
│   │   │           ├── security/              # Security related classes (e.g., filters, util)
│   │   │           │   ├── JwtRequestFilter.java # JWT filter
│   │   │           │   ├── JwtUtil.java      # JWT utilities
│   │   │           │   └── ...                # Other security files
│   │   │           ├── service/               # Service layer classes
│   │   │           │   ├── CustomUserDetailsService.java # User details service
│   │   │           │   └── ...                # Other service files
│   │   │           └── Main.java              # Main application class
│   │   └── resources/
│   │       ├── application.properties        # Application configuration
│   │       ├── static/                       # Static resources like HTML, CSS, JS
│   │       └── templates/                    # Thymeleaf templates (if any)
│   └── test/
│       ├── java/                             # Unit and integration tests
│       └── resources/                        # Test resources
├── .gitignore                                # Git ignore file
├── pom.xml                                   # Maven project object model file
└── README.md                                 # Project documentation
```

## Contributing

To contribute to this project:

1. Fork the repository.
2. Create your feature branch (`git checkout -b feature/AmazingFeature`).
3. Commit your changes (`git commit -am 'Add some AmazingFeature'`).
4. Push to the branch (`git push origin feature/AmazingFeature`).
5. Open a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Tomorrow's Work

- Implement API for vendor registration.
- Implement login API for vendors.
- Add API for vendors to list and manage services.
- Develop API for users to request services and view their status.
- Create API for vendors to view and update the status of service requests.

## Do Better 1% Everyday

Strive to improve the project incrementally, focusing on enhancing functionality, performance, and user experience.

## Quote of the Day 1 :
``` 
Time is Free But It's Priceless
You Can't Own 
It But You Can Use It
You Can't Keep It But You Can Spend It
Once You've Lost It You Can Never Get It Back 
```

```
```