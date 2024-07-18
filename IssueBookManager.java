/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.servlet;

import java.time.LocalDate;
import java.util.List;
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
    
    public void issueBook(int book_id,int student_id, LocalDate issued_date, LocalDate return_date)
    {
        issueBookDAO.addToIssuedBooks(new IssueBook(book_id, student_id, issued_date, return_date));
    }
    
    public void returnBook(int bookId, int studentId)
    {
        issueBookDAO.deleteFromIssuedBooks(bookId, studentId);
    }
    
    public List<IssueBook> displayIssuedBooks()
    {     
        return issueBookDAO.readAllIssuedBooks();
    }
}
