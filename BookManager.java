/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.servlet;

import java.util.List;

/**
 * Book service class for managing books.
 * @author Thokozani Mahlangu
 */
public class BookManager 
{
    private final BooksDAO bookDAO;
    
    public BookManager(BooksDAO bookDAO) {
        this.bookDAO = bookDAO;
    }
    
    public boolean doesBookExistsByISBN(String isbn)
    {
        return bookDAO.doesBookExistByISBN(isbn);
    }
                       
    public void addNewBook(String author, String title, String genre, int publicationYear, String ISBN)
    {      
        bookDAO.addBook(new Book(author, title, genre, publicationYear, ISBN));
    }
    
    public List<Book> displayAllBooks()
    {         
        return bookDAO.getAllBooks(); 
    }
        
    public void updateBook(int bookID, Book book)
    {
        bookDAO.updateBooks(bookID, book);
    }
    
    public void deleteBook(int bookID)
    {
        bookDAO.deleteBookById(bookID);
    }
    
    public List<Book> searchBook(String keyword) {      
        return bookDAO.searchBooksByKeyword(keyword);
    }
    
    public void updateIssuedBookStatus(int bookId) {
        bookDAO.updateBookStatus(bookId, "borrowed");
    }
    
    public void updateReturnedBookStatus(int bookId) {
        bookDAO.updateBookStatus(bookId, "available");
    }
    
    public String getBookStatus(int bookId) {
        return bookDAO.getBookStatusById(bookId);
    }
    
    public List<Book> displayAvailableBooks() {
        return bookDAO.getAvailableBooks();
    }
}
