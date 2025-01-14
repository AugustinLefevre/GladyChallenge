# Glady Backend Challenge

## Architecture and Technical Stack

### Technical Stack
- **Java 17**  
- **Spring Boot 3**  
- **MySQL**  
- **Hibernate**  

### Architecture
The project is structured as a **microservice architecture** (without a discovery server or gateway server).  
Communication is handled via **REST APIs** using **Webflux** in a blocking manner.  

- The **Company Service** manages data related to companies.  
- The **Deposition Service** manages data related to meal vouchers and gift cards.  

The **gift** and **meal** entities are stored in **separate tables** to anticipate future developments.  

### Description of Tests
- **Unit tests** have been implemented for the business logic.  
- **No integration tests** have been conducted.  