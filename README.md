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
## Table of Contents

- [Authentication](#authentication)
- [Admin Endpoints](#admin-endpoints)
- [Citizen Endpoints](#citizen-endpoints)
- [Complaint Endpoints](#complaint-endpoints)
- [Feedback Endpoints](#feedback-endpoints)
- [Team Endpoints](#team-endpoints)

## Authentication

### Sign In
- **Endpoint:** `POST /api/auth/signin`
- **Request Body:** `SignInDTO`
- **Response:** `JwtAuthResponse`
- **Description:** Authenticates the user and returns a JWT token.

### Sign Up
- **Endpoint:** `POST /api/auth/signup`
- **Request Body:** `RegisterDTO`
- **Response:** `ApiResponse`
- **Description:** Registers a new user in the system.

### Forgot Password
- **Endpoint:** `POST /api/auth/forgotpassword`
- **Request Param:** `email`
- **Response:** `ApiResponse`
- **Description:** Sends an OTP to the user's email for password reset.

### Verify OTP and Reset Password
- **Endpoint:** `POST /api/auth/verify-otp`
- **Request Body:** `VerifyOtpDTO`
- **Response:** `ApiResponse`
- **Description:** Verifies the OTP and allows the user to reset the password.

### Admin Sign In
- **Endpoint:** `POST /api/auth/admin/signin`
- **Request Body:** `SignInDTO`
- **Response:** `JwtAuthResponse`
- **Description:** Authenticates an admin user and returns a JWT token.

### Google Login Success
- **Endpoint:** `GET /api/auth/login`
- **Response:** `String`
- **Description:** Confirms successful login via Google OAuth2.

### Google Login Failure
- **Endpoint:** `GET /api/auth/google-login-failure`
- **Response:** `ApiResponse`
- **Description:** Returns a failure response if Google OAuth2 login fails.

## Admin Endpoints

### Add Role to Citizen
- **Endpoint:** `POST /api/admin/assign-role`
- **Request Body:** `RoleAssignmentDTO`
- **Response:** `ApiResponse`
- **Description:** Assigns a role to a citizen.

### Get All Complaints
- **Endpoint:** `GET /api/admin/complaints`
- **Response:** `List<ComplainToBeSHownOnFeedDTO>`
- **Description:** Retrieves all complaints.

### Get All Citizens
- **Endpoint:** `GET /api/admin/citizens`
- **Response:** `List<CitizenSummaryDto>`
- **Description:** Retrieves all citizens.

### Mark Complaint as Resolved
- **Endpoint:** `PATCH /api/admin/complaints/{complaintID}/resolved`
- **Path Variable:** `complaintID`
- **Response:** `String`
- **Description:** Marks a complaint as resolved.

### Mark Complaint as Open
- **Endpoint:** `PATCH /api/admin/complaints/{complaintID}/open`
- **Path Variable:** `complaintID`
- **Response:** `String`
- **Description:** Marks a complaint as open.

### Assign Team to Complaint
- **Endpoint:** `PUT /api/admin/assign-team/{complaintId}`
- **Path Variable:** `complaintId`
- **Response:** `String`
- **Description:** Assigns a team to a complaint.

## Citizen Endpoints

### Get Citizen By ID
- **Endpoint:** `GET /api/citizens/{id}`
- **Path Variable:** `id`
- **Response:** `CitizenDto`
- **Description:** Retrieves a citizen by their ID.

### Get All Complaints Registered By A Single Citizen
- **Endpoint:** `GET /api/citizens/get_all_complaints/{citizenId}`
- **Path Variable:** `citizenId`
- **Response:** `List<ComplaintDTO>`
- **Description:** Retrieves all complaints registered by a specific citizen.

### Update Partial Citizen Details
- **Endpoint:** `PATCH /api/citizens/{citizenId}`
- **Path Variable:** `citizenId`
- **Request Body:** `Map<String, Object>`
- **Response:** `CitizenDto`
- **Description:** Updates partial details of a citizen.

## Complaint Endpoints

### Add Complaint By Citizen ID
- **Endpoint:** `POST /api/complaints/{citizenId}`
- **Path Variable:** `citizenId`
- **Request Body:** `AddComplaintDTO`
- **Response:** `ApiResponse`
- **Description:** Adds a new complaint for a citizen.

### Get Complaint By ID
- **Endpoint:** `GET /api/complaints/{id}`
- **Path Variable:** `id`
- **Response:** `ComplaintDTO`
- **Description:** Retrieves a complaint by its ID.

### Get Complaints By Status
- **Endpoint:** `GET /api/complaints/status/{status}`
- **Path Variable:** `status`
- **Response:** `List<ComplaintDTO>`
- **Description:** Retrieves complaints based on their status.

### Delete Complaint By ID
- **Endpoint:** `DELETE /api/complaints/{complaintId}`
- **Path Variable:** `complaintId`
- **Response:** `ApiResponse`
- **Description:** Deletes a complaint by its ID.

### Get All Complaints
- **Endpoint:** `GET /api/complaints/`
- **Response:** `List<ComplainToBeSHownOnFeedDTO>`
- **Description:** Retrieves all complaints.

### Add Garbage Management Complaint
- **Endpoint:** `POST /api/complaints/garbage-management/{citizenId}`
- **Path Variable:** `citizenId`
- **Request Body:** `AddComplaintDTO`
- **Response:** `ApiResponse`
- **Roles:** `ADMIN`, `CITIZEN`
- **Description:** Adds a new garbage management complaint.

### Add Water Supply Complaint
- **Endpoint:** `POST /api/complaints/water-supply/{citizenId}`
- **Path Variable:** `citizenId`
- **Request Body:** `AddComplaintDTO`
- **Response:** `ApiResponse`
- **Roles:** `ADMIN`, `CITIZEN`
- **Description:** Adds a new water supply complaint.

### Add Electricity Management Complaint
- **Endpoint:** `POST /api/complaints/electricity-management/{citizenId}`
- **Path Variable:** `citizenId`
- **Request Body:** `AddComplaintDTO`
- **Response:** `ApiResponse`
- **Roles:** `ADMIN`, `CITIZEN`
- **Description:** Adds a new electricity management complaint.

### Add Road Repair Complaint
- **Endpoint:** `POST /api/complaints/road-repair/{citizenId}`
- **Path Variable:** `citizenId`
- **Request Body:** `AddComplaintDTO`
- **Response:** `ApiResponse`
- **Roles:** `ADMIN`, `CITIZEN`
- **Description:** Adds a new road repair complaint.

## Feedback Endpoints

### Add Feedback
- **Endpoint:** `POST /api/feedback/{complaintId}/{citizenId}`
- **Path Variables:** `complaintId`, `citizenId`
- **Request Body:** `FeedbackDTO`
- **Response:** `FeedbackDTO`
- **Description:** Adds feedback for a specific complaint.

### Get Feedback By ID
- **Endpoint:** `GET /api/feedback/{id}`
- **Path Variable:** `id`
- **Response:** `FeedbackDTO`
- **Description:** Retrieves feedback by its ID.

### Delete Feedback By ID
- **Endpoint:** `DELETE /api/feedback/{feedbackId}`
- **Path Variable:** `feedbackId`
- **Response:** `ApiResponse`
- **Description:** Deletes feedback by its ID.

## Team Endpoints

### Create Team
- **Endpoint:** `POST /api/teams`
- **Request Body:** `TeamDTO`
- **Response:** `TeamDTO`
- **Description:** Creates a new team.

### Get Team By ID
- **Endpoint:** `GET /api/teams/{id}`
- **Path Variable:** `id`
- **Response:** `TeamDTO`
- **Description:** Retrieves a team by its ID.

### Get All Teams
- **Endpoint:** `GET /api/teams`
- **Response:** `List<TeamDTO>`
- **Description:** Retrieves all teams.

### Update Team
- **Endpoint:** `PUT /api/teams/{id}`
- **Path Variable:** `id`
- **Request Body:** `TeamDTO`
- **Response:** `TeamDTO`
- **Description:** Updates a team by its ID.

### Delete Team
- **Endpoint:** `DELETE /api/teams/{id}`
- **Path Variable:** `id`
- **Response:** `Void`
- **Description:** Deletes a team by its ID.
