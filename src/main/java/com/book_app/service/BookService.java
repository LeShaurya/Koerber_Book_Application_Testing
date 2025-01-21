package com.book_app.service;

import com.book_app.entities.Book;
import com.book_app.exceptions.BookNotFoundException;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book addBook(Book book);
    boolean deleteBook(int id) throws BookNotFoundException;
    boolean updateBook(int id, Book book) throws BookNotFoundException;
    Book getBookById(int id) throws BookNotFoundException;
}
