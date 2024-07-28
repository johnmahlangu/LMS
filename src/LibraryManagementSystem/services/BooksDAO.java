/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LibraryManagementSystem.services;
import LibraryManagementSystem.domain.Book;
import LibraryManagementSystem.repositories.BookRepository;
import LibraryManagementSystem.DBConnection.ConnectionDB;
import java.util.*;
import java.sql.*;

/**
 * DAO class for managing books.
 * Implements BookRepository interface.
 * Provides methods that includes performing CRUD operations and searching.
 * @author Jonas Mahlangu
 */
public class BooksDAO implements BookRepository
{   
    private final Connection connection;
    
    public BooksDAO()
    {
        connection = ConnectionDB.getInstance().getConnection(); // Get the singleton instance of the database connection
    }
    
    /**
     * Checks if a book exists in the database by its ISBN.
     * @param isbn The ISBN of the book to check.
     * @return true if the book exists, false otherwise.
     */
    @Override
    public boolean doesBookExistByISBN(String isbn)
    {
        String query = "SELECT COUNT(*) FROM books WHERE ISBN = ?";
        
        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, isbn);
            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("ISBN already exists.");
                    return rs.getInt(1) > 0;
                }
                System.out.println("ISBN does not already exist.");
            }
        } catch (Exception e) {
            System.err.println("Error determining whether a book exists by ISBN: " + e);
        }
        return false;
    }
    
    /**
     * Retrieves all books from the database.
     * @return A list of all books.
     */
    @Override
    public List<Book> getAllBooks()
    {   
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";
        
        try (Statement st = connection.createStatement()) 
        {             
            ResultSet  rs = st.executeQuery(query);
             
            while (rs.next())
            {    
                books.add(mapResultSetToBook(rs));
            }
        }
        catch (SQLException e)
        {
            System.err.println("Error getting all books: " + e);
        }
        return books;
     }
    /**
     * Add a new book to the database.
     * @param book The book to add
     */
    @Override
    public void addBook(Book book)
    {
        
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO books (title, author, ISBN, publication_year, status) VALUES(?,?,?,?,?)"))
        {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getISBN());
            ps.setInt(4, book.getPublicationYear());
            ps.setString(5, book.getStatus());
             
            int rowsUpdated = ps.executeUpdate();  
            
            if (rowsUpdated > 0) {
                System.out.println("Book added to the database.");
            } 
        }
        catch (SQLException e) 
        {
            System.err.println("Error adding book: " + e);
        }
    }
    /**
     * Updates the details of an existing book in the database.
     * @param bookId The ID of the book to update.
     * @param book The book object containing updated details.
     */
    @Override
    public void updateBooks(int bookId, Book book) 
    {                 
        try
        {
            PreparedStatement ps = null;         
            StringBuilder sql = new StringBuilder("UPDATE books SET ");
            boolean first = true;
            
            if (book.getTitle() != null) {
                sql.append("title=?");
                first = false;
            }          
            if (book.getAuthor() != null) {
                if (!first) sql.append(", ");
                sql.append("author=?");
                first = false;
            }            
            if (book.getPublicationYear() != 0) {
                if (!first) sql.append(", ");
                sql.append("publication_year=?");
                first = false;
            }
            if (book.getISBN() != null) {
                if (!first) sql.append(", ");
                sql.append("ISBN=?");
            }                 
            sql.append(" WHERE book_id=?");           
            ps = connection.prepareStatement(sql.toString());            
            int row = 1;
            
            if (book.getTitle() != null) {
                ps.setString(row++, book.getTitle());
            }
            if (book.getAuthor() != null) {
                ps.setString(row++, book.getAuthor());          
            }               
            if (book.getPublicationYear() != 0) {
                ps.setInt(row++, book.getPublicationYear());
            }
            if (book.getISBN() != null) {
                ps.setString(row++, book.getISBN());
            }           
            ps.setInt(row, bookId);           
            int updatedRows = ps.executeUpdate();
            
            if (updatedRows > 0) {
                System.out.println("Book updated successfully.");
            }
            else {
                System.out.println("No Book found with the given book ID.");
            }          
        } 
        catch (SQLException e) {
            System.err.println("Error updating book: " + e);
        }
    }
    
    /**
     * Updates the status(borrowed or available) in the database.
     * @param bookId The ID of the book to update.
     * @param status The new status of the book.
     */
    @Override
    public void updateBookStatus(int bookId, String status)
    {
        String query = "UPDATE books SET status = ? WHERE book_id = ?";
        
        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, status);
            ps.setInt(2, bookId);
            
            int updatedRow = ps.executeUpdate();
            
            if (updatedRow > 0) {
                System.out.println("Book status updated successfully.");
            }
            else {
                System.out.println("No book found with the given book ID.");
            }
        }
        catch (SQLException e) {
            System.err.println("Error updating book status: " + e);
        }           
    }  
    
    /**
     * Retrieves the status of a book by its ID.
     * @param bookId The ID of the book.
     * @return The status of the book.
     */
    @Override
    public String getBookStatusById(int bookId)
    {
        String status = null;
        String query = "SELECT status FROM books WHERE book_id = ?";
        
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, bookId);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                status = rs.getString("status");
            }           
        } catch (SQLException e) {
            System.err.println("Error getting book status: " + e);
        }
        return status;
    }
    
    /**
     * Retrieves all available books from the database.
     * @return A list of available books.
     */
    @Override
    public List<Book> getAvailableBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books WHERE status = 'available'";
        
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) 
            {                
                books.add(mapResultSetToBook(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error getting books: " + e);
        }
        return books;
    }
    /**
     * Deletes a book from the database by its ID.
     * @param bookId The ID of the book to delete.
     */
    @Override
    public void deleteBookById(int bookId)
    {
        String query = "DELETE FROM books WHERE book_id=?";
        
        try (PreparedStatement ps = connection.prepareStatement(query)) 
        {
            ps.setInt(1, bookId);

            int deletedRow = ps.executeUpdate();
            
            if (deletedRow > 0) {
                System.out.println("Book deleted successfully.");
            }
            else {
                System.out.println("No Book found with the given book ID.");
            }
        } 
        catch (SQLException e)         
        {
            System.out.println("Error deleting book: " + e);
        }
    }
    /**
     * Searches fro books in the database that match the given keyword.
     * @param keyword The keyword to search for.
     * @return A list of books matching the keyword.
     */
    @Override
    public List<Book> searchBooksByKeyword(String keyword)
    {   
        List<Book> searchResult = new ArrayList<>();
        String query = "SELECT * FROM books WHERE book_id LIKE ? OR title LIKE ? OR author LIKE ? OR publication_year LIKE ? OR ISBN LIKE ? OR status LIKE ?";
        
        try (PreparedStatement ps = connection.prepareStatement(query))
        {
            for (int i = 1; i <= 6; i++) {
                ps.setString(i, "%" + keyword + "%");
            }
            
            try(ResultSet rs = ps.executeQuery())
            {
                while (rs.next())
                {
                    searchResult.add(mapResultSetToBook(rs));
                }
            }
            if (searchResult.isEmpty()) {
               System.out.println("No students found matching the search criteria.");
            } else {
               System.out.println("Book(s) with the search criteria found.");
            }
        }
        catch (SQLException e) {
                System.err.println("Error searching for books: " + e);
        }
        return searchResult;
    }
    /**
     * Maps a ResultSet row to a book object.
     * @param rs The resultSet to map.
     * @return A book object.
     * @throws SQLException if a SQL error occurs
     */
    
    private Book mapResultSetToBook(ResultSet rs) throws SQLException
    {
        Book book = new Book();
        
        book.setBookId(rs.getInt("book_id"));
        book.setAuthor(rs.getString("author"));
        book.setTitle(rs.getString("title"));
        book.setPublicationYear(rs.getInt("publication_year"));
        book.setISBN(rs.getString("ISBN"));
        book.setStatus(rs.getString("status"));
        
        return book;
    }
}
