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
- **MySQL**: relational database management system (RDBMS) for development and testing.
- **Lombok**: To reduce boilerplate code.

## Getting Started

### Prerequisites

Ensure you have the following installed:

- **Java 17 or later**
- **Maven**
- **Postman** (for testing API endpoints)

### Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/yourusername/MyMuncipleMateDummy.git
## API Endpoints

### Authentication

- **Sign In**: `POST /api/auth/signin`
  
  - **Request Body**: `SignInDTO`
  - **Response**: `JwtAuthResponse`

- **Sign Up**: `POST /api/auth/signup`
  
  - **Request Body**: `RegisterDTO`
  - **Response**: `ApiResponse`

- **Forgot Password**: `POST /api/auth/forgotpassword`
  
  - **Request Body**: `ForgotPasswordDTO`
  - **Response**: `ApiResponse`

### Admin

- **Add Role to Citizen**: `POST /api/admin/assign-role`
  
  - **Request Body**: `RoleAssignmentDTO`
  - **Response**: `ApiResponse`

- **Get All Complaints**: `GET /api/admin/complaints`
  
  - **Response**: `List<ComplainToBeSHownOnFeedDTO>`

- **Get All Citizens**: `GET /api/admin/citizens`
  
  - **Response**: `List<CitizenSummaryDto>`

- **Mark Complaint as Resolved**: `PATCH /api/admin/complaints/{complaintID}/resolved`
  
  - **Path Variable**: `complaintID`
  - **Response**: `String`

- **Mark Complaint as Open**: `PATCH /api/admin/complaints/{complaintID}/open`
  
  - **Path Variable**: `complaintID`
  - **Response**: `String`

- **Assign Team to Complaint**: `PUT /api/admin/assign-team/{complaintId}/{teamId}`
  
  - **Path Variables**: `complaintId`, `teamId`
  - **Response**: `String`

### Citizen

- **Get Citizen By ID**: `GET /api/citizens/{id}`
  
  - **Path Variable**: `id`
  - **Response**: `CitizenDto`

- **Get All Complaints Registered By A Single Citizen**: `GET /api/citizens/get_all_complaints/{citizenId}`
  
  - **Path Variable**: `citizenId`
  - **Response**: `List<ComplaintDTO>`

- **Update Complete Citizen Details**: `PUT /api/citizens/{citizenId}`
  
  - **Path Variable**: `citizenId`
  - **Request Body**: `CitizenDto`
  - **Response**: `CitizenDto`

- **Update Partial Citizen Details**: `PATCH /api/citizens/{citizenId}`
  
  - **Path Variable**: `citizenId`
  - **Request Body**: `Map<String, Object>`
  - **Response**: `CitizenDto`

## Complaint

- **Add Complaint By Citizen ID**: `POST /api/complaints/{citizenId}`
  - **Path Variable**: `citizenId`
  - **Request Body**: `AddComplaintDTO`
  - **Response**: `ApiResponse`

- **Get Complaint By ID**: `GET /api/complaints/{id}`
  - **Path Variable**: `id`
  - **Response**: `ComplaintDTO`

- **Get Complaints By Status**: `GET /api/complaints/status/{status}`
  - **Path Variable**: `status`
  - **Response**: `List<ComplaintDTO>`

- **Delete Complaint By ID**: `DELETE /api/complaints/{complaintId}`
  - **Path Variable**: `complaintId`
  - **Response**: `ApiResponse`

- **Get All Complaints**: `GET /api/complaints/`
  - **Response**: `List<ComplainToBeSHownOnFeedDTO>`

### New Endpoints for Complaint Types

- **Add Garbage Management Complaint**: `POST /api/complaints/garbage-management/{citizenId}`
  - **Path Variable**: `citizenId`
  - **Request Body**: `AddComplaintDTO`
  - **Response**: `ApiResponse`
  - **Roles**: `ADMIN`, `CITIZEN`

- **Add Water Supply Complaint**: `POST /api/complaints/water-supply/{citizenId}`
  - **Path Variable**: `citizenId`
  - **Request Body**: `AddComplaintDTO`
  - **Response**: `ApiResponse`
  - **Roles**: `ADMIN`, `CITIZEN`

- **Add Electricity Management Complaint**: `POST /api/complaints/electricity-management/{citizenId}`
  - **Path Variable**: `citizenId`
  - **Request Body**: `AddComplaintDTO`
  - **Response**: `ApiResponse`
  - **Roles**: `ADMIN`, `CITIZEN`

- **Add Road Repair Complaint**: `POST /api/complaints/road-repair/{citizenId}`
  - **Path Variable**: `citizenId`
  - **Request Body**: `AddComplaintDTO`
  - **Response**: `ApiResponse`
  - **Roles**: `ADMIN`, `CITIZEN`

### Feedback

- **Add Feedback**: `POST /api/feedback/{complaintId}/{citizenId}`
  
  - **Path Variables**: `complaintId`, `citizenId`
  - **Request Body**: `FeedbackDTO`
  - **Response**: `FeedbackDTO`

- **Get Feedback By ID**: `GET /api/feedback/{id}`
  
  - **Path Variable**: `id`
  - **Response**: `FeedbackDTO`

- **Delete Feedback By ID**: `DELETE /api/feedback/{feedbackId}`
  
  - **Path Variable**: `feedbackId`
  - **Response**: `ApiResponse`

### Team

- **Create Team**: `POST /api/teams`
  
  - **Request Body**: `TeamDTO`
  - **Response**: `TeamDTO`

- **Get Team By ID**: `GET /api/teams/{id}`
  
  - **Path Variable**: `id`
  - **Response**: `TeamDTO`

- **Get All Teams**: `GET /api/teams`
  
  - **Response**: `List<TeamDTO>`

- **Update Team**: `PUT /api/teams/{id}`
  
  - **Path Variable**: `id`
  - **Request Body**: `TeamDTO`
  - **Response**: `TeamDTO`

- **Delete Team**: `DELETE /api/teams/{id}`
  
  - **Path Variable**: `id`
  - **Response**: `Void`
