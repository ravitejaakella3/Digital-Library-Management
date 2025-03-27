package services;

import models.Book;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Manages the collection of books in the Digital Library System.
 * Handles CRUD operations and search functionality.
 */
public class LibraryManager {
    private final Map<Integer, Book> books;
    private static final Logger LOGGER = Logger.getLogger(LibraryManager.class.getName());

    public LibraryManager() {
        this.books = new HashMap<>();
        LOGGER.setLevel(Level.INFO);
    }

    public boolean addBook(Book book) {
        if (book == null) {
            LOGGER.warning("Attempted to add null book");
            return false;
        }
        if (books.containsKey(book.getId())) {
            LOGGER.warning("Attempted to add duplicate book with ID: " + book.getId());
            return false;
        }
        books.put(book.getId(), book);
        LOGGER.info("Added book: " + book);
        return true;
    }

    public void viewAllBooks() {
        if (books.isEmpty()) {
            LOGGER.info("No books in library");
            System.out.println("No books in the library.");
            return;
        }
        books.values().forEach(System.out::println);
        LOGGER.info("Displayed " + books.size() + " books");
    }

    public Book searchBookByIdOrTitle(String query) {
        if (query == null || query.trim().isEmpty()) {
            LOGGER.warning("Search attempted with null or empty query");
            return null;
        }

        try {
            int id = Integer.parseInt(query.trim());
            Book book = books.get(id);
            LOGGER.info("Searched by ID: " + id + ", Found: " + (book != null));
            return book;
        } catch (NumberFormatException e) {
            String searchQuery = query.trim().toLowerCase();
            Book found = books.values().stream()
                    .filter(book -> book.getTitle().toLowerCase().contains(searchQuery))
                    .findFirst()
                    .orElse(null);
            LOGGER.info("Searched by title: " + query + ", Found: " + (found != null));
            return found;
        }
    }

    public boolean updateBook(int id, String title, String author, String genre, boolean available) {
        if (!books.containsKey(id)) {
            LOGGER.warning("Attempted to update non-existent book with ID: " + id);
            return false;
        }
        try {
            Book newBook = new Book(id, title, author, genre, available);
            books.put(id, newBook);
            LOGGER.info("Updated book: " + newBook);
            return true;
        } catch (IllegalArgumentException e) {
            LOGGER.severe("Failed to update book with ID " + id + ": " + e.getMessage());
            throw e;
        }
    }

    public boolean deleteBook(int id) {
        Book removed = books.remove(id);
        if (removed != null) {
            LOGGER.info("Deleted book: " + removed);
            return true;
        }
        LOGGER.warning("Attempted to delete non-existent book with ID: " + id);
        return false;
    }
}
