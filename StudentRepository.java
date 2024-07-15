/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.servlet;
import java.util.*;
/**
 *
 * @author Thokozani Mahlangu
 */
public interface StudentRepository 
{   
    List<Student> readStudents();
    public boolean studentExistsByEmail(String email);
    void addToStudents(Student user);
    void updateStudents(int userID, Student updateUser);
    void deleteFromStudents(int userID);
    void searchStudents(String keyword);
}
