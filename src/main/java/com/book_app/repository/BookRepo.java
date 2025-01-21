package com.book_app.repository;

import com.book_app.entities.Book;
import com.book_app.exceptions.BookNotFoundException;

import java.util.List;

public interface BookRepo {
        List<Book> getAllBooks();
        Book addBook(Book book);
        boolean deleteBook(int id) throws BookNotFoundException;
        boolean updateBook(int id, Book book) throws BookNotFoundException;
        Book getBookById(int id) throws BookNotFoundException;
}
