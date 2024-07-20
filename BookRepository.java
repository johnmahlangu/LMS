/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.servlet;

import java.util.List;

/**
 *
 * @author Thokozani Mahlangu
 */
public interface BookRepository {
    List<Book> readFromBooks();
    List<Book> displayAvailableBooks();
    void updateBookStatus(int bookId, String status);
    public boolean bookExistsByISBN(String isbn);
    void addToBooks(Book book);
    void updateBooks(int bookID, Book updatedBook);
    void deleteBooks(int bookID);
    List<Book> searchBooks(String keyword);
}
