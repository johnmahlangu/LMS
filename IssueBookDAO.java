/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.servlet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thokozani Mahlangu
 */
public class IssueBookDAO implements IssueBookRepository 
{
    private Connection connection;
    
    public IssueBookDAO() {
        connection = ConnectionDB.getInstance().getConnection();
    }
     
    @Override
    public void addToIssuedBooks(IssueBook issueBook)
    {        
        String sql = "INSERT INTO issued_books (book_id, student_id, issued_date, due_date) VALUES(?,?,?,?)";
        
        try (PreparedStatement ps = connection.prepareStatement(sql))
        {          
            ps.setInt(1, issueBook.getBookId());
            ps.setInt(2, issueBook.getStudentId());
            ps.setDate(3, Date.valueOf(issueBook.getIssueDate()));
            ps.setDate(4, Date.valueOf(issueBook.getDueDate()));
                  
            ps.executeUpdate();
            
            System.out.println("Book issued successfully.");
        }
        catch(SQLException e)
        {
            System.out.println("Error issuing book: " + e.getMessage());
            e.printStackTrace();
        }
    }
   
    @Override
    public void deleteFromIssuedBooks(int bookId, int studentId)
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
            else {
                System.out.println("No book found with the given ID.");
            }
            
        } catch (SQLException e) 
        {
            System.err.println("Error returning book: " + e.getMessage());
        }
    }
    
    @Override
    public List<IssueBook> readAllIssuedBooks()
    {   
        List<IssueBook> issuedBooks = new ArrayList<>();
            
        try {
            String query = "SELECT * FROM issued_books";           
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while (rs.next()) {
                IssueBook issueBook = new IssueBook();
                
                issueBook.setBookId(rs.getInt("book_id"));
                issueBook.setStudentId(rs.getInt("student_id"));
                issueBook.setIssueDate(rs.getDate("issued_date").toLocalDate());
                issueBook.setDueDate(rs.getDate("due_date").toLocalDate());
                
                issuedBooks.add(issueBook);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return issuedBooks;
    }
}
