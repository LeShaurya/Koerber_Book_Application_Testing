package com.book_app.repository;

import com.book_app.entities.Book;
import com.book_app.exceptions.BookNotFoundException;
import com.book_app.factory.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepoImplementation implements BookRepo{
    private final Connection connection;

    public BookRepoImplementation() {
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM books");
            while(result.next())
                books.add(new Book(result.getInt("id"),
                        result.getString("isbn"),
                        result.getString("title"),
                        result.getString("author"),
                        result.getDouble("price")));
        } catch (SQLException e) {
            System.out.println("database error.");
        }
        return books;
    }

    @Override
    public Book addBook(Book book) {
        try {
            String query = "INSERT INTO books(isbn, title, author, price) VALUES(?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, book.getIsbn());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setDouble(4, book.getPrice());

            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                System.out.println("Failed to add book.");
            } else {
                System.out.println("Book added successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return book;
    }


    @Override
    public boolean deleteBook(int id) throws BookNotFoundException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM books WHERE id = ?");
            preparedStatement.setInt(1, id);
            int updates = preparedStatement.executeUpdate();
            if(updates == 0)
                throw new BookNotFoundException("Book with id " + id + " not found");
            return true;
        } catch (SQLException e) {
            System.out.println("database error.");
        }
        return false;
    }

    @Override
    public boolean updateBook(int id, Book book) throws BookNotFoundException {
        try {
            String query = "UPDATE books SET isbn = ?, title = ?, author = ?, price = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, book.getIsbn());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setDouble(4, book.getPrice());
            preparedStatement.setInt(5, id);

            int updates = preparedStatement.executeUpdate();
            if (updates == 0) {
                throw new BookNotFoundException("Book with id " + id + " not found.");
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return false;
    }


    @Override
    public Book getBookById(int id) throws BookNotFoundException {
        Book book = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM books WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet result=preparedStatement.executeQuery();
            if (result.next()){
                book= new Book(result.getInt("id"),
                        result.getString("isbn"),
                        result.getString("title"),
                        result.getString("author"),
                        result.getDouble("price"));
            } else {
                throw new BookNotFoundException("Book with id " + id + " not found");
            }
        } catch (SQLException e) {
            System.out.println("database error.");
        }
        return book;
    }
}
