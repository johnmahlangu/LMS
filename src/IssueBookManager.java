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
    private final IssueBookDAO issueBookDAO;

    public IssueBookManager(IssueBookDAO issuedBookDAO) {
        this.issueBookDAO = issuedBookDAO;
    }
    
    public void issueBook(int bookId,int student_id, LocalDate issueDate, LocalDate returnDate)
    {
        issueBookDAO.addIssuedBook(new IssueBook(bookId, student_id, issueDate, returnDate));
    }
    
    public void returnBook(int bookId, int studentId)
    {
        issueBookDAO.deleteIssuedBook(bookId, studentId);
    }
    
    public List<IssueBook> displayIssuedBooks()
    {     
        return issueBookDAO.getAllIssuedBooks();
    }
}
