/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.servlet;

import java.util.List;

/**
 *
 * @author Thokozani Mahlangu
 */
public class BookManager 
{
    private final BooksDAO bookDAO;
    
    public BookManager(BooksDAO bookDAO) {
        this.bookDAO = bookDAO;
    }
    
    public boolean bookExistsByISBN(String isbn)
    {
        return bookDAO.bookExistsByISBN(isbn);
    }
                       
    public void addNewBook(String author, String title, String genre, int publicationYear, String ISBN)
    {
        Book newBook = new Book(author, title, genre, publicationYear, ISBN);      
        bookDAO.addToBooks(newBook);
    }
    
    public List<Book> displayAllBooks()
    {
        List<Book> books = bookDAO.readFromBooks();    
        return books;
    }
        
    public void bookUpdate(int bookID, Book book)
    {
        bookDAO.updateBooks(bookID, book);
    }
    
    public void deleteBook(int bookID)
    {
        bookDAO.deleteBooks(bookID);
    }
    
    public List<Book> searchBook(String keyword) 
    {
        List<Book> searchResult = bookDAO.searchBooks(keyword);
        
        return searchResult;
    }
    
    public void issueBookStatus(int bookId) {
        bookDAO.updateBookStatus(bookId, "borrowed");
    }
    
    public void returnBookStatus(int bookId) {
        bookDAO.updateBookStatus(bookId, "available");
    }
}
