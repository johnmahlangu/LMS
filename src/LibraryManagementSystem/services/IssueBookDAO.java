/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LibraryManagementSystem.services;

import LibraryManagementSystem.DBConnection.ConnectionDB;
import LibraryManagementSystem.domain.IssueBook;
import LibraryManagementSystem.repositories.IssueBookRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for managing the issuing books.
 * Implements the IssueBookRepository interface.
 * Provides methods for adding, deleting, and reading issued books.
 * @author Jonas Mahlangu
 */
public class IssueBookDAO implements IssueBookRepository 
{
    private final Connection connection;
    
    public IssueBookDAO() {
        connection = ConnectionDB.getInstance().getConnection(); // Get the singleton instance of the database connection
    }
     
    /**
     * Adds issued book record to the database.
     * @param issueBook The issued book details
     */
    @Override
    public void addIssuedBook(IssueBook issueBook)
    {        
        String query = "INSERT INTO issued_books (book_id, student_id, issued_date, due_date) VALUES(?,?,?,?)";
        
        try (PreparedStatement ps = connection.prepareStatement(query))
        {          
            ps.setInt(1, issueBook.getBookId());
            ps.setInt(2, issueBook.getStudentId());
            ps.setDate(3, Date.valueOf(issueBook.getIssueDate()));
            ps.setDate(4, Date.valueOf(issueBook.getDueDate()));
                  
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book issued successfully.");
            }                       
        }
        catch(SQLException e)
        {
            System.err.println("Error issuing book: " + e);
        }
    }
   
    /**
     * Deletes an issued book record from the database.
     * @param bookId The book Id of the book to be deleted.
     * @param studentId The student ID of the student the book was issued to.
     */
    @Override
    public void deleteIssuedBook(int bookId, int studentId)
    {        
        String query = "DELETE FROM issued_books WHERE book_id = ? AND student_id = ?";
        
        try (PreparedStatement ps = connection.prepareStatement(query))
        {
            ps.setInt(1, bookId);
            ps.setInt(2, studentId);
            
            int rowsAffected = ps.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Book returned successfully.");
            }            
        } catch (SQLException e) 
        {
            System.err.println("Error returning book: " + e);
        }
    }
    
    /**
     * Reads all issued books records from the database.
     * @return A list of issued books.
     */
    @Override
    public List<IssueBook> getAllIssuedBooks()
    {   
        List<IssueBook> issuedBooks = new ArrayList<>();
            
        try {
            String query = "SELECT * FROM issued_books";           
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while (rs.next()) {                
                issuedBooks.add(mapResultSetToIssueBook(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error reading all books: " + e);
        }
        return issuedBooks;
    }
    
    private IssueBook mapResultSetToIssueBook(ResultSet rs) throws SQLException
    {
        IssueBook issueBook = new IssueBook();
        
        issueBook.setBookId(rs.getInt("book_id"));
        issueBook.setStudentId(rs.getInt("student_id"));
        issueBook.setIssueDate(rs.getDate("issued_date").toLocalDate());
        issueBook.setDueDate(rs.getDate("due_date").toLocalDate());
        
        return issueBook;
    }
}
