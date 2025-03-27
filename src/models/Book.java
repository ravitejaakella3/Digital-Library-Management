package models;

/**
 * Represents a Book in the Digital Library System.
 * Implements validation for all fields in the constructor.
 */
public class Book {
    private final int id;
    private String title;
    private String author;
    private String genre;
    private boolean available;
    
    private static final int MAX_TITLE_LENGTH = 100;
    private static final int MAX_AUTHOR_LENGTH = 50;
    private static final int MAX_GENRE_LENGTH = 30;

    public Book(int id, String title, String author, String genre, boolean available) {
        validateInput(id, title, author, genre);
        
        this.id = id;
        this.title = title.trim();
        this.author = author.trim();
        this.genre = genre.trim();
        this.available = available;
    }

    private void validateInput(int id, String title, String author, String genre) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive");
        }
        validateString(title, "Title", MAX_TITLE_LENGTH);
        validateString(author, "Author", MAX_AUTHOR_LENGTH);
        validateString(genre, "Genre", MAX_GENRE_LENGTH);
    }

    private void validateString(String value, String fieldName, int maxLength) {
        if (isNullOrEmpty(value)) {
            throw new IllegalArgumentException(fieldName + " cannot be empty");
        }
        if (value.length() > maxLength) {
            throw new IllegalArgumentException(fieldName + " cannot exceed " + maxLength + " characters");
        }
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public boolean isAvailable() { return available; }

    // Setters
    public void setTitle(String title) {
        validateString(title, "Title", MAX_TITLE_LENGTH);
        this.title = title.trim();
    }

    public void setAuthor(String author) {
        validateString(author, "Author", MAX_AUTHOR_LENGTH);
        this.author = author.trim();
    }

    public void setGenre(String genre) {
        validateString(genre, "Genre", MAX_GENRE_LENGTH);
        this.genre = genre.trim();
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return String.format("Book ID: %d, Title: %s, Author: %s, Genre: %s, Status: %s",
                id, title, author, genre, available ? "Available" : "Checked Out");
    }
}
