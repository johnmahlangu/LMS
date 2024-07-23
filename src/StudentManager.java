/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.servlet;

import java.util.List;

/**
 *  Student service class for managing students.
 * @author Jonas Mahlangu
 */
public class StudentManager 
{
    private StudentsDAO studentDAO;

    public StudentManager(StudentsDAO studentDAO) {
        this.studentDAO = studentDAO;
    }
   
    public boolean doesStudentExistByEmail(String email)
    {
        return studentDAO.doesStudentExistByEmail(email);
    }
    
    public boolean doesStudentExistsByStudentId(int studentId)
    {
        return studentDAO.doesStudentExistsByStudentId(studentId);
    }

    public void addStudent(String firstName, String lastName, String email)
    {       
        studentDAO.addStudent(new Student(firstName, lastName, email));    
    }
    
    public List<Student> displayAllStudents()
    {
       return studentDAO.getAllStudents();
    }
    
    public void updateStudent(int studentId, Student student)
    {
        studentDAO.updateStudent(studentId, student);
    }
    
    public void deleteStudent(int studentId)
    {
        studentDAO.deleteStudent(studentId);
    }
    
    public List<Student> searchStudent(String keyword)
    {       
        return studentDAO.searchStudents(keyword);
    }
}
