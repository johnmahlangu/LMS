# Library Management System (LMS)

This is a Library Management System (LMS) project developed in Java using Swing for the GUI and MySQL for the database. The system allows a librarian to perform various operations such as viewing books, issuing books, searching books, viewing borrowed books, adding books, returning books, and adding students.

## Features

- Add new books to the library.
- Display whether a book is available or issued.
- Update book details.
- Delete book.
- Search for books.
- View all books.
- Add new students.
- Update student details.
- Delete student.
- Search for students.
- View all students.
- View books that can be issued.
- Search for books that can be issued.
- Issue books to students.
- Return books.
- View issued books.

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- NetBeans IDE 
- MySQL Server

## Getting Started

### Clone the Repository

Clone the project from GitHub to your local machine using the following command:

git clone https://github.com/johnmahlangu/library-management-system.git

#### Setting Up the Database

To set up the initial database and tables, follow these steps:

1. Ensure you have MySQL installed and running on your system.

2. Open your MySQL command line interface (CLI).

#### Using MySQL CLI:

1. Open a terminal and log in to MySQL:

   mysql -u username -p

2. Enter your MySQL password when prompted

3. Run the following command to source the setup.sql file:
   
  source /path/to/setup.sql; (with the actual part to /setup.sql on your machine)

#### Configuring the Database Connection

1. In the root directory of the project, you will find a file named "db_config.properties.example"

2. Make a copy of this file and rename it to "db_config.properties".

3. Open the db_config.properties file and update the following properties with your database credentials:

  Replace "your_username" and "your_password" with your actual MySQL username and password.


##### Running the Application:

1. Open NetBeans IDE and run the project.
