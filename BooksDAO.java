/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.servlet;
import java.util.*;
import java.sql.*;
/**
 *
 * @author Thokozani Mahlangu
 */
public class BooksDAO implements BookRepository
{   
    private Connection connection;
    
    public BooksDAO()
    {
        connection = ConnectionDB.getInstance().getConnection();
    }
    
    @Override
    public boolean bookExistsByISBN(String isbn)
    {
        String query = "SELECT COUNT(*) FROM books WHERE ISBN = ?";
        
        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, isbn);
            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public List<Book> readFromBooks()
    {   
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";
        
        try(Statement st = connection.createStatement()) 
        {             
            ResultSet  rs = st.executeQuery(query);
             
            while (rs.next())
            {
                Book book = new Book();
                
                book.setBookId(rs.getInt("book_id"));
                book.setAuthor(rs.getString("author"));
                book.setTitle(rs.getString("title"));
                book.setStatus(rs.getString("status"));                 
                book.setPublicationYear(rs.getInt("publication_year"));
                book.setISBN(rs.getString("ISBN"));
                 
                books.add(book);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return books;
     }
    @Override
    public void addToBooks(Book book)
    {
        
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO books (title, author, ISBN, publication_year, status) VALUES(?,?,?,?,?)"))
        {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getISBN());
            ps.setInt(4, book.getPublicationYear());
            ps.setString(5, book.getStatus());
             
            ps.executeUpdate();    
            
            System.out.println("Book added succesfully.");
        }
        catch (SQLException e) 
        {
            System.err.println("Error adding book: " + e);
        }
    }
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
            
            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Book updated successfully.");
            } else {
                System.out.println("No book found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateBookStatus(int bookId, String status)
    {
        try(PreparedStatement ps = connection.prepareStatement("UPDATE books SET status = ? WHERE book_id = ?")) {
            ps.setString(1, status);
            ps.setInt(2, bookId);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }           
    }  
    @Override
    public String getBookStatus(int bookId)
    {
        String status = null;
        
        try (PreparedStatement ps = connection.prepareStatement("SELECT status FROM books WHERE book_id = ?")) {
            ps.setInt(1, bookId);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                status = rs.getString("status");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
    @Override
    public List<Book> displayAvailableBooks() {
        List<Book> books = new ArrayList<>();
        
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM books WHERE status = 'available'")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                
                book.setBookId(rs.getInt("book_id"));
                book.setAuthor(rs.getString("author"));
                book.setTitle(rs.getString("title"));
                book.setStatus(rs.getString("status"));                 
                book.setPublicationYear(rs.getInt("publication_year"));
                book.setISBN(rs.getString("ISBN"));
                 
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
     @Override
    public void deleteBooks(int bookID)
    {
         
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM books WHERE book_id=?")) 
        {
            ps.setInt(1, bookID);

            int rowsDeleted = ps.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Book deleted successfully.");
            } 
            else 
            {
                System.out.println("No book found with the given ID.");
            }
        } 
        catch (SQLException e)         
        {
            e.printStackTrace();
        }
    }      
     @Override
    public List<Book> searchBooks(String keyword)
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
                    Book book = new Book();
                    
                    book.setBookId(rs.getInt("book_id"));
                    book.setAuthor(rs.getString("author"));
                    book.setTitle(rs.getString("title"));
                    book.setPublicationYear(rs.getInt("publication_year"));
                    book.setISBN(rs.getString("ISBN"));
                    book.setStatus(rs.getString("status"));
                    
                    searchResult.add(book);
                }
            }       
            if (searchResult.isEmpty()) 
            {
                System.out.println("No books found matching the search criteria.");
            }
            else 
            {
                System.out.println("Books with search criteria found.");
            }
        }
        catch (SQLException e) {
                e.printStackTrace();
        }
        return searchResult;
    }
}
