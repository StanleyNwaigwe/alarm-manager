# Alarm Manager with Spotify Integration
## Description
This is a Spring Boot-based web application that allows users to manage alarms, each associated with a Spotify playlist. When an alarm goes off, the specified playlist will play via integration with the Spotify API.
## Features
- User registration and authentication.
- Create, update, and delete alarms.
- Associate Spotify playlists with alarms.
- Spotify API integration.
## Getting Started
### Prerequisites
- Java 17
- Maven
- MySQL Workbench
- Postman
- Spotify Developer Account

## Usage
- Open Postman to interact with the backend.
- Create new users by making a POST request to `/users/register`.
- Create, update, and delete alarms via `/alarms`.
- Associate playlists using Spotify playlist IDs.

## Database Schema
The application uses the following database tables:

### `users`
- `id`: BIGINT (Primary key)
- `username`: VARCHAR(255) (Unique)
- `password`: VARCHAR(255)

### `playlists`
- `id`: BIGINT (Primary key)
- `name`: VARCHAR(255)
- `spotify_id`: VARCHAR(255) (Unique)

### `alarms`
- `id`: BIGINT (Primary key)
- `name`: VARCHAR(255)
- `time`: TIME
- `playlist_id`: Reference to `playlists(id)`

## API Endpoints

### User Endpoints:
- `POST /users/register` – Register a new user.
    - Body: `{ "username": "yourname", "password": "yourpassword" }`

### Alarm Endpoints:
- `GET /alarms` – Retrieve all alarms.
- `POST /alarms` – Create a new alarm.
    - Body: `{ "name": "Morning Alarm", "time": "08:00", "playlistId": "playlist123" }`

## Technologies Used
- Spring Boot (Java)
- MySQL
- Spotify Web API
- Postman (for API testing)

## To-Do
- [ ] Implement user authentication with Spring Security.
- [ ] Add OAuth integration for Spotify login.
- [ ] Create a frontend using React or Vue.js, UI.
- [ ] Exception Handling
- [ ] Validation
- [ ] Unit/Integration Testing

## Problems While Building
- Lombok automatic getter and setter wasn't made me encounter issues during mvn clean install with alarm.getTime "cannot find symbol -> alarm"
  - I implemented my own getter and setters and resolved the issue.


## Contact
Feel free to reach out for questions, feedback, or suggestions:
- Email: stanleycnwaigwe@yahoo.com


