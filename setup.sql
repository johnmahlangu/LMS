CREATE DATABASE IF NOT EXISTS lms;

USE lms;

CREATE TABLE books (
    book_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50),
    author VARCHAR(25),
    ISBN VARCHAR(25) UNIQUE,
    publication_year INT,
    status VARCHAR(10) DEFAULT 'available'
);

CREATE TABLE students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(50)
);

CREATE TABLE issued_books (
    book_id INT,
    student_id INT,
    issued_date DATE,
    due_date DATE,
    PRIMARY KEY (book_id, student_id),
    FOREIGN KEY (book_id) REFERENCES books(book_id),
    FOREIGN KEY (student_id) REFERENCES students(student_id)
);
