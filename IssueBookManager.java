/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.servlet;

import java.time.LocalDate;
/**
 *
 * @author Thokozani Mahlangu
 */
public class IssueBookManager 
{
    private IssuedBookDAO issuedBookDAO;

    public IssueBookManager(IssuedBookDAO issuedBookDAO) {
        this.issuedBookDAO = issuedBookDAO;
    }
    
    public void borrowedBook(int book_id,int user_id, LocalDate issued_date, LocalDate return_date)
    {
        BorrowedBook issueBook = new BorrowedBook(book_id, user_id, issued_date, return_date);
        issuedBookDAO.addToBorrowedBooks(issueBook);
    }
    
    public void returnBookById(int bookID)
    {
        issuedBookDAO.deleteFromBorrowedBooks(bookID);
    }
    
    public void borrowedByStudentId(int studentId)
    {
        issuedBookDAO.readFromBorrowedBooks(studentId);
    }
}
