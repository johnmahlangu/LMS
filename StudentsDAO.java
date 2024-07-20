/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.servlet;
import java.sql.*;
import java.util.*;
/**
 *
 * @author Thokozani Mahlangu
 */
public class StudentsDAO implements StudentRepository
{  
    private Connection connection;
    
    public StudentsDAO() {
        connection = ConnectionDB.getInstance().getConnection();
    }
    
    @Override
    public boolean studentExistsByEmail(String email)
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
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean studentExistsByStudentId(int studentId)
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
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public List<Student> readStudents()
    {        
        List<Student> students = new ArrayList<>();
        
        try(Statement st = connection.createStatement())
        {
            ResultSet rs = st.executeQuery("SELECT * FROM students");
            
            while (rs.next())
            {
                Student student = new Student();  
                
                student.setStudentId(rs.getInt("student_id"));
                student.setFirstName(rs.getString("first_name"));
                student.setLastName(rs.getString("last_name"));
                student.setEmail(rs.getString("email"));
                
                students.add(student);
            }                  
        }
        catch (SQLException e) {
            e.printStackTrace();          
        } 
        return students;
    }
    @Override
    public void addToStudents(Student student)
    {
        String query = "INSERT INTO students (first_name, last_name, email) VALUES(?,?,?)";
        
        try(PreparedStatement ps = connection.prepareStatement(query);)
        {           
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setString(3, student.getEmail());
                       
            ps.executeUpdate();
            
            System.out.println("Student added succesfully.");
        }
        catch (SQLException e)
        {
            System.err.println("Error adding book: " + e);
        }
    }
 
    @Override
    public void updateStudents(int studentId, Student student)
    {        
        try
        {
            PreparedStatement ps = null;           
            StringBuilder sql = new StringBuilder("UPDATE students SET ");
            boolean first = true;
            
            if (student.getFirstName() != null) {
                sql.append("first_name=?");
                first = false;
            }
            if (student.getLastName() != null) {
                if (!first) sql.append(", ");
                sql.append("last_name=?");
                first = false;
            }
            if (student.getEmail() != null) {
                if (!first) sql.append(", ");
                sql.append("email=?");
                first = false;
            }
            
            sql.append(" WHERE student_id =?");            
            ps = connection.prepareStatement(sql.toString());
            
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
                System.out.println("No student found with the given ID");
            }
        }
        catch (SQLException e) {
            System.err.println("Error updating student book: " + e);
        } 
    }
    @Override
    public void deleteFromStudents(int userID)
    {
        String query = "DELETE FROM students WHERE student_id = ?";
                
        try (PreparedStatement ps = connection.prepareStatement(query))
        {
            ps.setInt(1, userID);
            
            int rowsDeleted = ps.executeUpdate();
            
            if (rowsDeleted > 0){
                System.out.println("User deleted successfully.");
            }
            else {
                System.out.println("No user found with the given ID.");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
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
                    Student student = new Student();
                    
                    student.setStudentId(rs.getInt("student_id"));
                    student.setFirstName(rs.getString("first_name"));
                    student.setLastName(rs.getString("last_name"));
                    student.setEmail(rs.getString("email"));
                    
                    searchResult.add(student);
                }
            }
            if (searchResult.isEmpty()) {
                System.out.println("No users found matching the search criteria.");
            }
            else {
                System.out.println("Search result:");
                
                for (Student user: searchResult) {
                    System.out.println(user);
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return searchResult;
    }   
}
