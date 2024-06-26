# Documentation

## Overview

This project is a Library Management System developed using Spring Boot, which allows users to manage books, patrons, and borrowing records in a library setting. The system provides RESTful APIs for various operations, including adding, updating, deleting, and retrieving books and patrons, as well as borrowing and returning books.

## Features

- CRUD operations for managing books and patrons
- Record borrowing and returning of books by patrons
- Exception handling for graceful error responses
- Logging using Aspect-Oriented Programming (AOP)
- Caching of frequently accessed data for improved performance
- All critical operations are performed within transactions, ensuring data consistency and integrity.
- Utilizes PostgreSQL database for data storage.

## Technologies Used

- Java
- Spring Boot
- Spring Security
- Maven

## Setup Instructions

1. Clone the repository to your local machine.
2. Navigate to the project directory.
3. run `docker compose up` to run the database .
4. Open the project in your preferred IDE.
5. Run the application using Maven or your IDE.

## API Endpoints

### Books

- `GET /api/v1/book`: Retrieve all books
- `GET /api/v1/book/{id}`: Retrieve a book by ID
- `POST /api/v1/book`: Add a new book
  - Request Body:
    ```json
    {
      "title": "Book Title",
      "author": "Author Name",
      "publicationYear": "Year",
      "isbn": "ISBN Number"
    }
    ```
- `PUT /api/v1/book/{id}`: Update a book by ID
  - Request Body:
    ```json
    {
      "title": "Updated Title",
      "author": "Updated Author",
      "publicationYear": "Updated Year",
      "isbn": "Updated ISBN"
    }
    ```
- `DELETE /api/v1/book/{id}`: Delete a book by ID

### Patrons

- `GET /api/v1/patron`: Retrieve all patrons
- `GET /api/v1/patron/{id}`: Retrieve a patron by ID
- `POST /api/v1/patron`: Add a new patron
  - Request Body:
    ```json
    {
      "name": "Patron Name",
      "email": "patron@example.com",
      "phoneNumber": "1234567890"
    }
    ```
- `PUT /api/v1/patron/{id}`: Update a patron by ID
  - Request Body:
    ```json
    {
      "name": "Updated Patron Name",
      "email": "updated_patron@example.com",
      "phoneNumber": "9876543210"
    }
    ```
- `DELETE /api/v1/patron/{id}`: Delete a patron by ID

### Borrowing Records

- `POST /api/v1/borrow/{bookId}/patron/{patronId}`: Borrow a book
- `PUT /api/v1/return/{bookId}/patron/{patronId}`: Return a borrowed book

## Testing

Unit tests are provided to validate the functionality of API endpoints. These tests cover various scenarios, including retrieving, adding, updating, and deleting books and patrons, as well as borrowing and returning books by patrons.
