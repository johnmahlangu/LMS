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
public class IssueBook 
{
    private int bookId;
    private int studentId;
    private LocalDate issued_date;
    private LocalDate return_date;

    // Constructors
    public IssueBook() {
    }

    public IssueBook(int bookId, int studentId, LocalDate issued_date, LocalDate return_date) {
        this.bookId = bookId;
        this.studentId = studentId;
        this.issued_date = issued_date;
        this.return_date = return_date;
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

    public LocalDate getIssued_date() 
    {
        return issued_date;
    }
    
    public void setIssued_date(LocalDate issued_date) 
    {
        this.issued_date = issued_date;
    }

    public LocalDate getReturn_date() 
    {
        return return_date;
    }

    public void setReturn_date(LocalDate return_date) 
    {
        this.return_date = return_date;
    }
}
