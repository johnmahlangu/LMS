/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.servlet;

import java.sql.*;

/**
 *
 * @author Thokozani Mahlangu
 */
public class IssuedBookDAO implements IssuedBookRepo 
{       
    @Override
    public void addToBorrowedBooks(BorrowedBook borrowedBook)
    {
        Connection connection = ConnectionDB.getInstance().getConnection();
        
        String sql = "INSERT INTO borrowed_books (book_id, student_id, borrow_date, return_date) VALUES(?,?,?,?)";
        
        try (PreparedStatement ps = connection.prepareStatement(sql))
        {          
            ps.setInt(1, borrowedBook.getBookId());
            ps.setInt(2, borrowedBook.getStudentId());
            ps.setDate(3, Date.valueOf(borrowedBook.getIssued_date()));
            ps.setDate(4, Date.valueOf(borrowedBook.getReturn_date()));
                  
            ps.executeUpdate();
            
            System.out.println("Book issued successfully.");
        }
        catch(SQLException e)
        {
            System.err.println("Error issuing book: " + e.getMessage());
        }
    }
    @Override
    public void deleteFromBorrowedBooks(int issuedID)
    {
        Connection connection = ConnectionDB.getInstance().getConnection();
        
        String sql = "DELETE FROM borrowed_books WHERE book_id = ?";
        
        try (PreparedStatement ps = connection.prepareStatement(sql))
        {
            ps.setInt(1, issuedID);
            
            int rowsAffected = ps.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Book returned successfully.");
            } else {
                System.out.println("No book found with the given ID.");
            }
            
        } catch (SQLException e) 
        {
            System.err.println("Error returning book: " + e.getMessage());
        }
    }
    
    @Override
    public void readFromBorrowedBooks(int StudentId)
    {
        Connection connection = ConnectionDB.getInstance().getConnection();
        
        String sql = "SELECT DISTINCT borrowed_books.*, books.title " +
                     "FROM borrowed_books " +
                     "JOIN books ON borrowed_books.book_id = books.book_id " +
                     "WHERE borrowed_books.student_id = ?";
        
        try (PreparedStatement ps = connection.prepareStatement(sql))
        {
            ps.setInt(1, StudentId);        
            try (ResultSet rs = ps.executeQuery()) 
            {
                while(rs.next()) {
                    int bookId = rs.getInt("book_id");
                    int title = rs.getInt("student_id");
                    Date borrowDate = rs.getDate("borrow_date");
                    Date returnDate = rs.getDate("return_date");
                    
                    System.out.printf("Book ID: %d, Title: %s, Borrowed Date: %s, Return Date: %s\n", 
                                      bookId, title, borrowDate, returnDate);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching borrowed books: " + e.getMessage());
        }           
    }
}
