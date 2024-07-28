/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package LibraryManagementSystem.repositories;

import LibraryManagementSystem.domain.Book;
import java.util.List;

/**
 *
 * @author Jonas Mahlangu
 */
public interface BookRepository {
    List<Book> getAllBooks();
    String getBookStatusById(int bookId);
    List<Book> getAvailableBooks();
    void updateBookStatus(int bookId, String status);
    boolean doesBookExistByISBN(String isbn);
    void addBook(Book book);
    void updateBooks(int bookID, Book updatedBook);
    void deleteBookById(int bookID);
    List<Book> searchBooksByKeyword(String keyword);
}
