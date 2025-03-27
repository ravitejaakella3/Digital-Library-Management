# Digital Library Management System

## Overview
A Java-based library management system that enables librarians to perform CRUD operations on books and track availability through a command-line interface.

## Features
- Add new books with unique IDs
- View all books in the library
- Search books by ID or title
- Update book details
- Delete books from catalog
- Track book availability status

## Project Structure
```
Digital Library/
├── src/
│   ├── app/
│   │   └── DigitalLibrary.java    # Main application
│   ├── models/
│   │   └── Book.java             # Book entity
│   └── services/
│       └── LibraryManager.java    # Business logic
└── test/
    └── LibraryManagerTest.java    # Unit tests
```

## Requirements
- Java 17 or higher
- Maven 3.8+

## Quick Start
1. Download the latest release
2. Extract the ZIP file
3. Run the application:
```batch
run.bat
```

## Build from Source
```batch
mvn clean package
java -jar target/digital-library-1.0.0.jar
```

## Input Constraints
| Field        | Validation Rules                    |
|--------------|-------------------------------------|
| Book ID      | Positive integer, unique            |
| Title        | Non-empty, max 100 characters       |
| Author       | Non-empty, max 50 characters        |
| Genre        | Non-empty, max 30 characters        |
| Availability | "Available" or "Checked Out"         |

## Testing
```batch
mvn test
```

## License
MIT License - See LICENSE file for details