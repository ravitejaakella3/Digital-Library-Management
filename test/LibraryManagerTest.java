package test;

import models.Book;
import services.LibraryManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LibraryManagerTest {
    private LibraryManager library;
    private Book testBook;
    private static final int MAX_TITLE_LENGTH = 100;
    private static final int MAX_AUTHOR_LENGTH = 50;
    private static final int MAX_GENRE_LENGTH = 30;

    @BeforeEach
    void setUp() {
        library = new LibraryManager();
        testBook = new Book(1, "Java", "James Gosling", "Programming", true);
    }

    @Test
    void testAddBook() {
        assertTrue(library.addBook(testBook));
        assertEquals(testBook, library.searchBookByIdOrTitle("1"));
    }

    @Test
    void testAddDuplicateBook() {
        library.addBook(testBook);
        assertFalse(library.addBook(testBook));
    }

    @Test
    void testAddNullBook() {
        assertFalse(library.addBook(null));
    }

    @Test
    void testSearchByTitle() {
        library.addBook(testBook);
        assertEquals(testBook, library.searchBookByIdOrTitle("Java"));
    }

    @Test
    void testSearchByNonExistentTitle() {
        assertNull(library.searchBookByIdOrTitle("Python"));
    }

    @Test
    void testUpdateBook() {
        library.addBook(testBook);
        assertTrue(library.updateBook(1, "Python", "Guido", "Programming", false));
        Book updated = library.searchBookByIdOrTitle("1");
        assertEquals("Python", updated.getTitle());
        assertEquals("Guido", updated.getAuthor());
        assertFalse(updated.isAvailable());
    }

    @Test
    void testUpdateNonExistentBook() {
        assertFalse(library.updateBook(999, "Python", "Guido", "Programming", true));
    }

    @Test
    void testDeleteBook() {
        library.addBook(testBook);
        assertTrue(library.deleteBook(1));
        assertNull(library.searchBookByIdOrTitle("1"));
    }

    @Test
    void testDeleteNonExistentBook() {
        assertFalse(library.deleteBook(999));
    }

    @Test
    void testBookConstructorValidation() {
        assertThrows(IllegalArgumentException.class, () -> 
            new Book(0, "Java", "James", "Programming", true));
        assertThrows(IllegalArgumentException.class, () -> 
            new Book(1, "", "James", "Programming", true));
        assertThrows(IllegalArgumentException.class, () -> 
            new Book(1, "Java", "", "Programming", true));
        assertThrows(IllegalArgumentException.class, () -> 
            new Book(1, "Java", "James", "", true));
    }

    @Test
    void testSearchCaseInsensitive() {
        library.addBook(testBook);
        assertEquals(testBook, library.searchBookByIdOrTitle("java"));
        assertEquals(testBook, library.searchBookByIdOrTitle("JAVA"));
    }

    @Test
    void testSearchWithWhitespace() {
        library.addBook(testBook);
        assertEquals(testBook, library.searchBookByIdOrTitle(" Java "));
    }

    @Test
    void testSearchWithPartialMatch() {
        library.addBook(testBook);
        assertEquals(testBook, library.searchBookByIdOrTitle("Ja"));
    }

    @Test
    void testTitleLengthValidation() {
        String longTitle = "a".repeat(MAX_TITLE_LENGTH + 1);
        assertThrows(IllegalArgumentException.class, () ->
            new Book(1, longTitle, "Author", "Genre", true));
    }

    @Test
    void testAuthorLengthValidation() {
        String longAuthor = "a".repeat(MAX_AUTHOR_LENGTH + 1);
        assertThrows(IllegalArgumentException.class, () ->
            new Book(1, "Title", longAuthor, "Genre", true));
    }

    @Test
    void testGenreLengthValidation() {
        String longGenre = "a".repeat(MAX_GENRE_LENGTH + 1);
        assertThrows(IllegalArgumentException.class, () ->
            new Book(1, "Title", "Author", longGenre, true));
    }

    @Test
    void testUpdateWithInvalidData() {
        library.addBook(testBook);
        assertThrows(IllegalArgumentException.class, () ->
            library.updateBook(1, "", "Author", "Genre", true));
    }

    @Test
    void testSearchWithSpecialCharacters() {
        Book specialBook = new Book(2, "C# & Java", "Author", "Programming", true);
        library.addBook(specialBook);
        assertEquals(specialBook, library.searchBookByIdOrTitle("C#"));
    }

    @Test
    void testMultipleBooks() {
        Book book1 = new Book(1, "Java", "Author1", "Programming", true);
        Book book2 = new Book(2, "Python", "Author2", "Programming", false);
        
        library.addBook(book1);
        library.addBook(book2);
        
        assertEquals(book1, library.searchBookByIdOrTitle("Java"));
        assertEquals(book2, library.searchBookByIdOrTitle("Python"));
    }

    @Test
    void testUpdateAvailabilityOnly() {
        library.addBook(testBook);
        assertTrue(library.updateBook(1, testBook.getTitle(), 
            testBook.getAuthor(), testBook.getGenre(), false));
        
        Book updated = library.searchBookByIdOrTitle("1");
        assertFalse(updated.isAvailable());
        assertEquals(testBook.getTitle(), updated.getTitle());
    }
}
