package com.book_app.service;

import com.book_app.entities.Book;
import com.book_app.exceptions.BookNotFoundException;
import com.book_app.repository.BookRepo;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepo bookRepo;

    @InjectMocks
    private BookServiceImplementation bookServiceImplementation;

    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book(1, "978-3-16-148410-0", "The Great Adventure", "John Doe", 119.99);
    }

    @AfterEach
    void tearDown() {
        book = null;
    }

    @Tag("dev")
    @Test
    void getAllBooks() {
        List<Book> books = Collections.singletonList(book);
        Mockito.when(bookRepo.getAllBooks()).thenReturn(books);

        List<Book> result = bookServiceImplementation.getAllBooks();
        assertEquals(1, result.size());

    }

    @Tag("dev")
    @Test
    void addBook() {
        Book book = new Book(4, "978-3-16-148410-0", "The Great Adventure", "John Doe", 119.99);
        Mockito.when(bookRepo.addBook(book)).thenReturn(book);
        Book result = bookServiceImplementation.addBook(book);
        assertNotNull(result);
        assertEquals(result, book);
    }

    @Tag("dev")
    @Test
    void deleteBookSuccess() throws BookNotFoundException {
        int bookId = 1;
        Mockito.when(bookRepo.deleteBook(bookId)).thenReturn(true);
        boolean result = bookServiceImplementation.deleteBook(bookId);
        assertTrue(result);
    }

    @Tag("dev")
    @Test
    void deleteBookFailure() throws BookNotFoundException {
        int bookId = 1;
        Mockito.when(bookRepo.deleteBook(bookId)).thenThrow(new BookNotFoundException("Book Not found"));
        assertThrows(BookNotFoundException.class, () -> bookServiceImplementation.deleteBook(bookId));
    }

    @Tag("dev")
    @Test
    void updateBookSuccess() throws BookNotFoundException {
        int bookId = 1;
        Mockito.when(bookRepo.updateBook(bookId, book)).thenReturn(true);
        boolean result = bookServiceImplementation.updateBook(bookId, book);
        assertTrue(result);
    }

    @Tag("dev")
    @Test
    void updateBookFailure() throws BookNotFoundException {
        int bookId = 1;
        Mockito.when(bookRepo.updateBook(bookId, book)).thenThrow(new BookNotFoundException("Book Not found"));
        assertThrows(BookNotFoundException.class, () -> bookServiceImplementation.updateBook(bookId, book));
    }

    @Tag("dev")
    @Test
    void getBookByIdSuccess() throws BookNotFoundException {
        int bookId = 1;
        Mockito.when(bookRepo.getBookById(bookId)).thenReturn(book);
        Book result = bookServiceImplementation.getBookById(bookId);
        assertNotNull(result);
        assertEquals("The Great Adventure", result.getTitle());
    }

    @Tag("dev")
    @Test
    void getBookByIdFailure() throws BookNotFoundException {
        int bookId = 1;
        Mockito.when(bookRepo.getBookById(bookId)).thenThrow(new BookNotFoundException("Book Not found"));
        assertThrows(BookNotFoundException.class, () -> bookServiceImplementation.getBookById(bookId));
    }
}