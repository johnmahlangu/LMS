/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.servlet;

/**
 *
 * @author Thokozani Mahlangu
 */
public class Book 
{    
    private String author;
    private String title;   
    private int publicationYear;
    private String publisher;
    private String ISBN;
    private int bookId;

    public Book()
    {}
    
    public Book(String author, String title, String publisher, int publicationYear, String ISBN) {
        this.author = author;
        this.title = title;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
        this.ISBN = ISBN;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookID) {
        this.bookId = bookID;
    }
    
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }
}
