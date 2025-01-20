package com.book_app.service;

import com.book_app.entities.Book;
import com.book_app.exceptions.BookNotFoundException;
import com.book_app.repository.BookRepo;
import com.book_app.repository.BookRepoImplementation;

import java.util.List;

public class BookServiceImplementation implements BookService{
    BookRepo bookRepo = new BookRepoImplementation();
    @Override
    public List<Book> getAllBooks() {
        return bookRepo.getAllBooks();
    }

    @Override
    public Book addBook(Book book) {
        return  bookRepo.addBook(book);
    }

    @Override
    public void deleteBook(int id) throws BookNotFoundException {
        bookRepo.deleteBook(id);
    }

    @Override
    public void updateBook(int id, Book book) throws BookNotFoundException {
        bookRepo.updateBook(id, book);
    }

    @Override
    public Book getBookById(int id) throws BookNotFoundException {
        return bookRepo.getBookById(id);
    }
}
