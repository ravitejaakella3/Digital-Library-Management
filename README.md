# Digital Library Management System

## Overview
A Java command-line application for managing a digital library system. Allows librarians to perform CRUD operations on books and track their availability.

## Features
- Add new books with unique IDs
- View all books in the library
- Search books by ID or title
- Update book details
- Delete books from the catalog
- Track book availability status

## Project Structure
```
digital-library-management/
├── src/
│   ├── app/
│   │   └── DigitalLibrary.java       # Main application & UI
│   ├── models/
│   │   └── Book.java                 # Book entity
│   └── services/
│       └── LibraryManager.java       # Business logic
└── test/
    └── LibraryManagerTest.java       # Unit tests
```

## Prerequisites
- Java Development Kit (JDK) 17 or higher
- JUnit 5.8.2 for testing

## Quick Start

1. Clone the repository:
```batch
git clone https://github.com/ravitejaakella3/Digital-Library-Management
cd digital-library-management
```

2. Create output directory:
```batch
mkdir out
```

3. Compile the application:
```batch
javac -d out -sourcepath src src/app/DigitalLibrary.java
```

4. Run the application:
```batch
java -cp out app.DigitalLibrary
```

## Testing

1. Download JUnit:
```batch
mkdir lib
curl -o lib/junit-platform-console-standalone-1.8.2.jar https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.8.2/junit-platform-console-standalone-1.8.2.jar
```

2. Compile and run tests:
```batch
javac -d out -cp ".;lib/junit-platform-console-standalone-1.8.2.jar" src/models/Book.java src/services/LibraryManager.java test/LibraryManagerTest.java
java -jar lib/junit-platform-console-standalone-1.8.2.jar --class-path out --scan-class-path
```

## Usage Guide

### Input Constraints
| Field        | Validation Rules                    |
|--------------|-------------------------------------|
| Book ID      | Positive integer, unique            |
| Title        | Non-empty, max 100 characters       |
| Author       | Non-empty, max 50 characters        |
| Genre        | Non-empty, max 30 characters        |
| Availability | "Available" or "Checked Out" only    |

### Menu Options
1. Add Book - Add a new book with details
2. View All Books - Display complete catalog
3. Search Book - Find by ID or title
4. Update Book - Modify book details
5. Delete Book - Remove a book
6. Exit - Close application

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
