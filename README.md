# EventEase — Event Booking System

EventEase is a desktop-based event booking application developed using **Java Swing** and **PostgreSQL**. The system provides separate user and administrator workflows for managing events, making bookings, and maintaining event-related data.

## Overview

EventEase is designed to simplify the process of managing and booking events through a centralized desktop application.

Users can create an account, browse available events, view event details, make bookings, and manage their existing bookings. Administrators can manage events and monitor booking-related information through a dedicated dashboard.

## Key Features

### User Module
- User registration and authentication
- Browse available events
- View event details and seat availability
- Book events
- View booking history
- Manage existing bookings

### Admin Module
- Dedicated administrator dashboard
- Create and manage events
- Update event information
- Delete events
- Manage event availability
- View booking and revenue information

### User Interface
- Desktop GUI built with Java Swing
- Custom reusable UI components
- Rounded buttons, panels, and input fields
- Consistent application theme
- Modern Swing styling using FlatLaf

## Technology Stack

| Technology | Purpose |
|---|---|
| Java | Application development |
| Java Swing | Desktop user interface |
| PostgreSQL | Relational database |
| JDBC | Database connectivity |
| FlatLaf | UI styling and theming |
| VS Code | Development environment |

## Architecture

The application follows a layered architecture that separates the user interface, business data models, and database operations.

```text
User Interface
      │
      ▼
   DAO Layer
      │
      ▼
 PostgreSQL
```

### Project Structure

```text
EventEase/
│
├── dao/
│   ├── BookingDAO.java
│   ├── EventDAO.java
│   └── UserDAO.java
│
├── db/
│   └── DBConnection.java
│
├── model/
│   ├── Booking.java
│   ├── Event.java
│   └── User.java
│
├── ui/
│   ├── LoginPage.java
│   ├── SignupPage.java
│   ├── HomePage.java
│   ├── BookEventPage.java
│   ├── MyBookingsPage.java
│   ├── AdminPage.java
│   └── components/
│
├── lib/
│   ├── flatlaf-3.7.jar
│   └── postgresql-42.7.13.jar
│
└── Main.java
```

## Database

EventEase uses **PostgreSQL** as its relational database and communicates with it through the PostgreSQL JDBC driver.

The application manages data related to:

- Users
- Events
- Bookings

Database operations are organized through dedicated DAO classes such as `UserDAO`, `EventDAO`, and `BookingDAO`.

## Getting Started

### Prerequisites

Make sure the following are installed:

- Java JDK 17 or later
- PostgreSQL
- Git
- Java-compatible IDE such as VS Code or IntelliJ IDEA

### Clone the Repository

```bash
git clone https://github.com/your-username/EventEase.git
cd EventEase
```

### Database Configuration

Create a PostgreSQL database and configure the connection details in:

```text
db/DBConnection.java
```

Example:

```java
private static final String URL =
        "jdbc:postgresql://localhost:5432/eventease";

private static final String USER = "postgres";
private static final String PASSWORD = "your_password";
```

Replace the credentials with your local PostgreSQL configuration.

> Do not commit real database passwords or other sensitive credentials to a public repository.

### Run the Application

Add the required libraries from the `lib/` directory to the project classpath and run:

```text
Main.java
```

The application will launch the EventEase login interface.

## Future Enhancements

Potential improvements include:

- Online payment integration
- Email-based booking confirmations
- Event search and filtering
- Event categories
- Cloud database integration
- Improved authentication and password security
- Booking analytics and reporting
- Standalone application packaging

## Author

**Nikhil Bhatt**

B.Tech — Computer Science & Engineering

---

If you find the project useful, consider giving the repository a star.
