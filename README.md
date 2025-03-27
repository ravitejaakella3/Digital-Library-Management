# Digital Library Management System

## Overview
A Java-based library management system that enables librarians to perform book management operations through a command-line interface, implementing CRUD operations and availability tracking.

## Features
- Add new books with unique IDs
- View all books in the library
- Search books by ID or title
- Update book details
- Delete books from the catalog
- Track book availability status

## Project Structure
```
Digital Library/
├── src/
│   ├── app/
│   │   └── DigitalLibrary.java       # Main application & UI
│   ├── models/
│   │   └── Book.java                 # Book entity class
│   └── services/
│       └── LibraryManager.java       # Business logic & data management
└── test/
    └── LibraryManagerTest.java       # Unit tests
```

## Requirements
- Java Development Kit (JDK) 17 or higher
- JUnit 5.8.2 for unit testing

## Build and Run

### Setting up the project
```batch
mkdir out
mkdir lib
curl -o lib/junit-platform-console-standalone-1.8.2.jar https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.8.2/junit-platform-console-standalone-1.8.2.jar
```

### Compile and Run
```batch
# Compile the application
javac -d out -sourcepath src src/app/DigitalLibrary.java

# Run the application
java -cp out app.DigitalLibrary
```

### Running Tests
```batch
# Compile tests
javac -d out -cp ".;lib/junit-platform-console-standalone-1.8.2.jar" src/models/Book.java src/services/LibraryManager.java test/LibraryManagerTest.java

# Run tests
java -jar lib/junit-platform-console-standalone-1.8.2.jar --class-path out --scan-class-path
```

## Usage Guide

### Menu Options
1. **Add Book**
   - Enter unique Book ID
   - Provide title, author, and genre
   - Set availability status

2. **View All Books**
   - Displays complete book catalog
   - Shows all book details

3. **Search Book**
   - Search by ID or title
   - Case-insensitive search
   - Supports partial matching

4. **Update Book**
   - Modify any book detail
   - Update availability status
   - Maintains ID integrity

5. **Delete Book**
   - Remove book by ID
   - Confirmation required

### Input Constraints
| Field        | Validation Rules                    |
|--------------|-------------------------------------|
| Book ID      | Positive integer, unique            |
| Title        | Non-empty, max 100 characters       |
| Author       | Non-empty, max 50 characters        |
| Genre        | Non-empty, max 30 characters        |
| Availability | "Available" or "Checked Out" only    |

## Technical Details

### Implementation Features
- In-memory storage using HashMap
- Comprehensive input validation
- Exception handling for all operations
- Logging system for operation tracking
- Unit test coverage for all features

### Error Handling
- Input validation for all fields
- Duplicate ID prevention
- Clear error messages
- Graceful error recovery

## Development

### Running in VS Code
1. Open project folder in VS Code
2. Install "Java Extension Pack"
3. Open integrated terminal
4. Run build and test commands

### Running Tests in VS Code
1. Click Testing icon in sidebar
2. Click Run Tests button
3. View results in Testing panel

## License
MIT License - See LICENSE file for details
