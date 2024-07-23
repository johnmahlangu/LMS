/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.servlet;
import java.sql.*;
import java.util.*;
/**
 * DAO class for managing students.
 * Implements StudentRepository interface.
 * Provides methods for performing CRUD operations on books, searching for books etc..
 * @author Jonas Mahlangu
 */
public class StudentsDAO implements StudentRepository
{  
    private Connection connection;
    
    public StudentsDAO() {
        connection = ConnectionDB.getInstance().getConnection(); // Get the singleton instance of the database connection
    }
    
    /**
     * Checks if a student exists in the database by their email.
     * @param email The email to check
     * @return True if the student exists, false otherwise.
     */
    @Override
    public boolean doesStudentExistByEmail(String email)
    {
        String query = "SELECT COUNT(*) FROM students WHERE email = ?";
        
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error checking whether student exists by email: " + e);
        }
        return false;
    }
    
    /**
     * Checks if a student exists in the database by their student ID.
     * @param studentId The student ID to check.
     * @return True if the student exists, false otherwise.
     */
    @Override
    public boolean doesStudentExistsByStudentId(int studentId)
    {
        String query = "SELECT COUNT(*) FROM students WHERE student_id = ?";
        
        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setInt(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error checking whether student exists by student ID: " + e);
        }
        return false;
    }
    
    /**
     * Retrieves a list of all students from the database.
     * @return A list of students.
     */
    @Override
    public List<Student> getAllStudents()
    {        
        List<Student> students = new ArrayList<>();
        
        try(Statement st = connection.createStatement())
        {
            ResultSet rs = st.executeQuery("SELECT * FROM students");
            
            while (rs.next())
            {               
                students.add(mapResultSetToStudent(rs));
            }                  
        }
        catch (SQLException e) {
            System.out.println("Error reading all students: " + e);          
        } 
        return students;
    }
    /**
     * Add student to the database.
     * @param student The student to add.
     */
    @Override
    public void addStudent(Student student)
    {
        String query = "INSERT INTO students (first_name, last_name, email) VALUES(?,?,?)";
        
        try(PreparedStatement ps = connection.prepareStatement(query);)
        {           
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setString(3, student.getEmail());
                       
            int rowAffected = ps.executeUpdate();
            
            if (rowAffected > 0) {
                System.out.println("Student added succesfully.");
            }
        }
        catch (SQLException e)
        {
            System.err.println("Error adding student: " + e);
        }
    }
    
    /**
     * Update existing student details in the database.
     * @param studentId The student ID of the student to update.
     * @param student The student object to update.
     */
    @Override
    public void updateStudent(int studentId, Student student)
    {        
        try
        {
            PreparedStatement ps = null;           
            StringBuilder query = new StringBuilder("UPDATE students SET ");
            boolean first = true;
            
            if (student.getFirstName() != null) {
                query.append("first_name=?");
                first = false;
            }
            if (student.getLastName() != null) {
                if (!first) query.append(", ");
                query.append("last_name=?");
                first = false;
            }
            if (student.getEmail() != null) {
                if (!first) query.append(", ");
                query.append("email=?");
                first = false;
            }
            
            query.append(" WHERE student_id =?");            
            ps = connection.prepareStatement(query.toString());
            
            int row = 1;
            
            if (student.getStudentId() != 0) {
                ps.setInt(row++, student.getStudentId());
            }
            if (student.getFirstName() != null) {
                ps.setString(row++, student.getFirstName());
            }
            if (student.getLastName() != null) {
                ps.setString(row++, student.getLastName());
            }
            if (student.getEmail() != null) {
                ps.setString(row++, student.getEmail());
            }
            
            ps.setInt(row, studentId);
            
            int rowsUpdated = ps.executeUpdate();
            
            if (rowsUpdated > 0) {
                System.out.println("Student updated successfully.");
            } else {
                System.out.println("No student found with the given student ID");
            }
        }
        catch (SQLException e) {
            System.err.println("Error updating student details: " + e);
        } 
    }
    
    /**
     * Deletes a student from he database.
     * @param studentID The student ID of the student to delete.
     */
    @Override
    public void deleteStudent(int studentID)
    {
        String query = "DELETE FROM students WHERE student_id = ?";
                
        try (PreparedStatement ps = connection.prepareStatement(query))
        {
            ps.setInt(1, studentID);
            
            int rowsDeleted = ps.executeUpdate();
            
            if (rowsDeleted > 0){
                System.out.println("Student deleted successfully.");
            }
            else {
                System.out.println("No student found with the given student ID.");
            }
        }
        catch (SQLException e){
            System.err.println("Error deleting student: " + e);
        }
    }
    
    /**
     * Searches for student(s) in the database based on keyword.
     * @param keyword The keyword to search for.
     * @return Student(s) matching the search criteria.
     */
    @Override
    public List<Student> searchStudents(String keyword)
    {
        List<Student> searchResult = new ArrayList<>();
        String query = "SELECT * FROM students WHERE student_id LIKE ? OR first_name LIKE ? OR last_name LIKE ? OR email LIKE ?";
        
        try (PreparedStatement ps = connection.prepareStatement(query))
        {
           for (int i = 1; i <= 4; i++) {
               ps.setString(i, "%" + keyword + "%");
           }
            
            try (ResultSet rs = ps.executeQuery())
            {
                while (rs.next())
                {                   
                    searchResult.add(mapResultSetToStudent(rs));
                }
            }
            if (searchResult.isEmpty()) {
                System.out.println("No student(s) found matching the search criteria.");
            }
            else {
                System.out.println("Student(s) found matching the search criteria.");
            }
        }
        catch (SQLException e) {
            System.out.println("Error searching student(s): " + e);
        }
        return searchResult;
    }
    
    private Student mapResultSetToStudent(ResultSet rs) throws SQLException
    {
        Student student = new Student();
        
        student.setStudentId(rs.getInt("student_id"));
        student.setFirstName(rs.getString("first_name"));
        student.setLastName(rs.getString("last_name"));
        student.setEmail(rs.getString("email"));
        
        return student;
    }
}
