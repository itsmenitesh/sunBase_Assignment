# Customer Management System

This project is a CRUD (Create, Read, Update, Delete) application for managing customer information. It includes backend APIs for handling customer data, synchronization with a remote API.

## Technologies Used

- Java
- Spring Boot
- MySQL
- JWT Authentication
- RestTemplate

## Project Structure

- **`src/main/java/com/sunbase/customerbackend`**: Contains the main Java source code.
  - **`controller`**: Handles incoming HTTP requests.
  - **`model`**: Defines the data model (e.g., Customer class).
  - **`repo`**: Manages data access and manipulation.
  - **`service`**: Implements business logic.
  
- **`src/main/resources`**: Configuration files and static resources.
  
- **`pom.xml`**: Maven project configuration file.


# Customer Management System

Access the application at [http://localhost:8080](http://localhost:8080)

## API Endpoints

- **GET /api/customers/sync**: Synchronize customer list with a remote API.
- **GET /api/customers**: Get all customers.
- **GET /api/customers/{id}**: Get a customer by ID.
- **POST /api/customers**: Create a new customer.
- **PUT /api/customers/{id}**: Update an existing customer.
- **DELETE /api/customers/{id}**: Delete a customer.
- **GET /api/customers/paged**: Get customers with pagination.

## User Roles

- The system requires authentication with the role of **ROLE_ADMIN** to see all the database of customer for that User Entity has been created.

## Testing with Postman

Use the provided API endpoints for testing with Postman:

- **GET /api/customers/sync**: Synchronize customer list (requires Bearer token).
- **GET /api/customers**: Get all customers.
- **GET /api/customers/{id}**: Get a customer by ID.
- **POST /api/customers**: Create a new customer.
- **PUT /api/customers/{id}**: Update an existing customer.
- **DELETE /api/customers/{id}**: Delete a customer.
- **GET /api/customers/paged**: Get customers with pagination.

## Obtain Bearer Token

Authenticate with the remote API to obtain the Bearer token:

- **Path**: https://qa.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp
- **Method**: POST
- **Body**:
  ```json
  {
    "login_id": "test@sunbasedata.com",
    "password": "Test@123"
  }


## Setup

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/customer-management-system.git
