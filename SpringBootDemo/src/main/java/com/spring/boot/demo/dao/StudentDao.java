package com.spring.boot.demo.dao;

import java.util.Collection;

import com.spring.boot.demo.entity.Student;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public interface StudentDao {
    Collection<Student> getAllStudents();

    Student getStudentById(int id);

    void removeStudentById(int id);

    void updateStudent(Student student);

    void insertStudentToDb(Student student);
}
