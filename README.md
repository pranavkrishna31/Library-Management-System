# Library Management System (Console-Based)

## Overview
This is a console-based Library Management System developed in Java.
The system is designed based on the given Excel requirements and focuses on implementing core library functionalities such as book management, issue/return flow, fine calculation, and validations.

## Features

Authentication
- Admin and User login (role-based access)

Book Management (Admin)
- Add new books
- Update book quantity
- View all books

User Management
- Add users
- Membership creation and update

Search & Availability
- Search books by name
- Check availability status

Book Issue
- Issue book if available
- Automatically sets return date (15 days from issue)

Book Return
- Return issued books
- Updates book quantity

Fine Calculation
- Late return fine = 10 per day
- Fine payment functionality

Validation
- Mandatory field checks
- Invalid input handling
- Logical constraints enforced

## Approach

- Implemented using Object-Oriented Programming (OOP)
- Modular structure:
  - model → Data classes
  - service → Business logic
  - util → Validation logic
- In-memory storage using Java collections (ArrayList)

## Project Structure

src/
 ├── Main.java
 ├── model/
 │     ├── Book.java
 │     ├── User.java
 │     ├── Transaction.java
 │     ├── Membership.java
 ├── service/
 │     ├── LibraryService.java
 ├── util/
 │     ├── Validation.java

## How to Run

1. Navigate to src folder
2. Compile:
   javac Main.java service/LibraryService.java model/*.java util/*.java
3. Run:
   java Main

## Sample Flow

Login → admin → View Books  
Login → user → Search Book → Issue Book → Return Book → Pay Fine

## Preloaded Data

- 15 Books
- 3 Users
- Memberships (6/12/24 months)

## Note

- This is a console-based implementation
- Focus is on logic, validation, and system design
- UI/Frontend and database were not required as per problem scope

## Technologies Used

- Java
- OOP Concepts
- Collections Framework

## Author
KONDAPALLI PRANAV KRISHNA
HU22CSEN0101534
Developed as part of coding assessment
