/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.servlet;

import java.util.List;

/**
 *
 * @author Thokozani Mahlangu
 */
public interface IssueBookRepository 
{
    void addToBorrowedBooks(IssueBook borrowedBoo);
    void deleteFromBorrowedBooks(int issuedID);
    List<IssueBook> readAllIssuedBooks();
}
