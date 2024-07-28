/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package LibraryManagementSystem.repositories;
import LibraryManagementSystem.domain.Student;
import java.util.*;
/**
 *
 * @author Thokozani Mahlangu
 */
public interface StudentRepository 
{   
    List<Student> getAllStudents();
    boolean doesStudentExistByEmail(String email);
    boolean doesStudentExistsByStudentId(int studentId);
    void addStudent(Student user);
    void updateStudent(int userID, Student updateUser);
    void deleteStudent(int userID);
    List<Student> searchStudents(String keyword);
}
