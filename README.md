# Forum Website

A simple forum website built with Spring Boot and Thymeleaf that allows anonymous users to create posts and replies.

## Features
- View post list and hot posts
- Create new posts
- Reply to posts
- Automatic HOT marking for posts with 3+ replies
- No authentication required - all users are anonymous

## Tech Stack
- Spring Boot
- Thymeleaf template engine
- H2 Database
- Bootstrap 5

## Setup
1. Clone the repository
2. Run `mvn spring-boot:run`
3. Visit `http://localhost:8080`

## Development
The application uses H2 in-memory database. The H2 console is enabled and can be accessed at `http://localhost:8080/h2-console` with:
- JDBC URL: `jdbc:h2:mem:forumdb`
- Username: `sa`
- Password: (empty)
