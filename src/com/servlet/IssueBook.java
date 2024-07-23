/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.servlet;

import java.time.LocalDate;
/**
 *
 * @author Jonas Mahlangu
 */
public class IssueBook 
{
    private int bookId;
    private int studentId;
    private LocalDate issueDate;
    private LocalDate dueDate;

    // Constructors
    public IssueBook() {
    }

    public IssueBook(int bookId, int studentId, LocalDate issued_date, LocalDate return_date) {
        this.bookId = bookId;
        this.studentId = studentId;
        this.issueDate = issued_date;
        this.dueDate = return_date;
    }

    public int getBookId() 
    {
        return bookId;
    }
    
    public void setBookId(int bookId) 
    {
        this.bookId = bookId;
    }

    public int getStudentId() 
    {
        return studentId;
    }

    public void setStudentId(int studentId) 
    {
        this.studentId = studentId;
    }

    public LocalDate getIssueDate() 
    {
        return issueDate;
    }
    
    public void setIssueDate(LocalDate issueDate) 
    {
        this.issueDate = issueDate;
    }

    public LocalDate getDueDate() 
    {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) 
    {
        this.dueDate = dueDate;
    }
}
