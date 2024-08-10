# MyMunicipalMate Backend

Welcome to the MyMunicipalMate backend! This backend service handles authentication, role management, citizen services, complaints, feedback, and team management.

## Table of Contents

1. [Features](#features)
2. [Technologies](#technologies)
3. [Getting Started](#getting-started)
   - [Prerequisites](#prerequisites)
   - [Installation](#installation)
   - [Configuration](#configuration)
4. [API Endpoints](#api-endpoints)
   - [Authentication](#authentication)
   - [Admin](#admin)
   - [Citizen](#citizen)
   - [Complaint](#complaint)
   - [Feedback](#feedback)
   - [Team](#team)
5. [Running Tests](#running-tests)
6. [Contributing](#contributing)
7. [License](#license)

## Features

- **Authentication**: Secure login and JWT-based authentication.
- **Role Management**: Admins can assign and remove roles.
- **Citizen Management**: CRUD operations for citizen details.
- **Complaint Management**: Create, update, and retrieve complaints.
- **Feedback Management**: Create, retrieve, and delete feedback.
- **Team Management**: Admins can manage teams.

## Technologies

- **Spring Boot**: Framework for building Java-based backend applications.
- **Spring Security**: For authentication and authorization.
- **JWT**: For secure token-based authentication.
- **Hibernate**: ORM for database operations.
- **H2 Database**: In-memory database for development and testing.
- **Lombok**: To reduce boilerplate code.

## Getting Started

### Prerequisites

Ensure you have the following installed:

- **Java 11 or later**
- **Maven**
- **Postman** (for testing API endpoints)

### Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/yourusername/MyMunicipalMate-backend.git
