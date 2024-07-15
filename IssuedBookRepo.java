/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.servlet;
/**
 *
 * @author Thokozani Mahlangu
 */
public interface IssuedBookRepo 
{
    void addToBorrowedBooks(BorrowedBook borrowedBoo);
    void deleteFromBorrowedBooks(int issuedID);
    void readFromBorrowedBooks(int userID);
}
