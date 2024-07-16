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
    private IssueBookDAO issueBookDAO;

    public IssueBookManager(IssueBookDAO issuedBookDAO) {
        this.issueBookDAO = issuedBookDAO;
    }
    
    public void borrowedBook(int book_id,int user_id, LocalDate issued_date, LocalDate return_date)
    {
        IssueBook issueBook = new IssueBook(book_id, user_id, issued_date, return_date);
        issueBookDAO.addToBorrowedBooks(issueBook);
    }
    
    public void returnBookById(int bookID)
    {
        issueBookDAO.deleteFromBorrowedBooks(bookID);
    }
    
    public void borrowedByStudentId(int studentId)
    {
        issueBookDAO.readFromBorrowedBooks(studentId);
    }
}
