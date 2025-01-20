package com.book_app;

import com.book_app.entities.Book;
import com.book_app.service.BookService;
import com.book_app.service.BookServiceImplementation;


public class Main {
    public static void main(String[] args) {
        BookService bookService = new BookServiceImplementation();
        getAllBooks(bookService);


        Book book = bookService.getBookById(1);
        book.setPrice(book.getPrice() + 100);
        bookService.updateBook(1, book);
        getAllBooks(bookService);

    }

    private static void getAllBooks(BookService bookService) {
        bookService.getAllBooks().forEach(System.out::println);
    }
}