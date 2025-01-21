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
        try {
            bookRepo.deleteBook(id);
        } catch (BookNotFoundException e) {
            throw new BookNotFoundException("Book Not found");
        }
    }

    @Override
    public void updateBook(int id, Book book) throws BookNotFoundException {
        try {
            bookRepo.updateBook(id, book);
        } catch (BookNotFoundException e) {
            throw new BookNotFoundException("Book Not found");
        }
    }

    @Override
    public Book getBookById(int id) throws BookNotFoundException {
        try {
            return bookRepo.getBookById(id);
        } catch (BookNotFoundException e) {
            throw new BookNotFoundException("Book Not found");
        }
    }
}
