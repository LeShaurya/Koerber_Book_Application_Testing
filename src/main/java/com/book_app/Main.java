package com.book_app;

import com.book_app.entities.Book;
import com.book_app.service.BookService;
import com.book_app.service.BookServiceImplementation;


public class Main {
    public static void main(String[] args) {
        BookService bookService = new BookServiceImplementation();
        getAllBooks(bookService);

        System.out.println("Adding new book...");
        Book newBook = new Book("978-0-12345-678-9", "The Art of Java Programming", "Alice Cooper", 49.99);
        bookService.addBook(newBook);
        getAllBooks(bookService);

        System.out.println("Deleting a book...");
        bookService.deleteBook(6);
        getAllBooks(bookService);

        System.out.println("Updating a book");
        Book book = bookService.getBookById(5);
        if(book != null) {
        book.setPrice(book.getPrice() + 100);
        bookService.updateBook(5, book);
        }
        getAllBooks(bookService);



    }

    private static void getAllBooks(BookService bookService) {
        bookService.getAllBooks().forEach(System.out::println);
    }
}