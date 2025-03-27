package app;

import models.Book;
import services.LibraryManager;
import java.util.Scanner;

/**
 * Main application class for managing the Digital Library.
 * Handles user interaction and input validation.
 */
public class DigitalLibrary {
    private static final String AVAILABLE = "available";
    private static final String CHECKED_OUT = "checked out";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibraryManager library = new LibraryManager();

        while (true) {
            displayMenu();
            int choice = getValidChoice(scanner);
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> handleAddBook(scanner, library);
                case 2 -> library.viewAllBooks();
                case 3 -> handleSearchBook(scanner, library);
                case 4 -> handleUpdateBook(scanner, library);
                case 5 -> handleDeleteBook(scanner, library);
                case 6 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice! Please enter a number between 1 and 6.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n1. Add Book");
        System.out.println("2. View All Books");
        System.out.println("3. Search Book");
        System.out.println("4. Update Book");
        System.out.println("5. Delete Book");
        System.out.println("6. Exit");
        System.out.print("Enter choice: ");
    }

    private static int getValidChoice(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid number!");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void handleAddBook(Scanner scanner, LibraryManager library) {
        int id = getValidId(scanner, library, "Enter Book ID: ");
        String title = getValidInput(scanner, "Enter Title: ", "Error: Title cannot be empty.");
        String author = getValidInput(scanner, "Enter Author: ", "Error: Author cannot be empty.");
        String genre = getValidInput(scanner, "Enter Genre: ", "Error: Genre cannot be empty.");
        boolean available = getAvailabilityInput(scanner);

        if (library.addBook(new Book(id, title, author, genre, available))) {
            System.out.println("Book added successfully!");
        }
    }

    private static void handleSearchBook(Scanner scanner, LibraryManager library) {
        System.out.print("Enter Book ID or Title to search: ");
        String query = scanner.nextLine();
        Book book = library.searchBookByIdOrTitle(query);
        System.out.println(book != null ? book : "Book not found.");
    }

    private static void handleUpdateBook(Scanner scanner, LibraryManager library) {
        int id = getValidId(scanner, library, "Enter Book ID to update: ");
        String title = getValidInput(scanner, "Enter new Title: ", "Error: Title cannot be empty.");
        String author = getValidInput(scanner, "Enter new Author: ", "Error: Author cannot be empty.");
        String genre = getValidInput(scanner, "Enter new Genre: ", "Error: Genre cannot be empty.");
        boolean available = getAvailabilityInput(scanner);

        if (library.updateBook(id, title, author, genre, available)) {
            System.out.println("Book updated successfully!");
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void handleDeleteBook(Scanner scanner, LibraryManager library) {
        System.out.print("Enter Book ID to delete: ");
        int id = getValidChoice(scanner);
        if (library.deleteBook(id)) {
            System.out.println("Book deleted successfully!");
        } else {
            System.out.println("Book not found.");
        }
    }

    private static int getValidId(Scanner scanner, LibraryManager library, String prompt) {
        while (true) {
            System.out.print(prompt);
            int id = getValidChoice(scanner);
            scanner.nextLine(); // Consume newline
            
            if (prompt.contains("update") || prompt.contains("delete")) {
                return id;
            }
            
            if (library.searchBookByIdOrTitle(String.valueOf(id)) != null) {
                System.out.println("Error: Book ID already exists. Please enter a unique ID.");
            } else if (id <= 0) {
                System.out.println("Error: Book ID must be a positive number.");
            } else {
                return id;
            }
        }
    }

    private static String getValidInput(Scanner scanner, String prompt, String errorMessage) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println(errorMessage);
            }
        } while (input.isEmpty());
        return input;
    }

    private static boolean getAvailabilityInput(Scanner scanner) {
        while (true) {
            System.out.print("Enter Availability (Available/Checked Out): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals(AVAILABLE)) {
                return true;
            } else if (input.equals(CHECKED_OUT)) {
                return false;
            }
            System.out.println("Error: Please enter either 'Available' or 'Checked Out'.");
        }
    }
}
