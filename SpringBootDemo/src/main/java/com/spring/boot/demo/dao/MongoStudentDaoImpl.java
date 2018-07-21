package com.spring.boot.demo.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.spring.boot.demo.entity.Student;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
@Repository
@Qualifier("mongoData")
public class MongoStudentDaoImpl implements StudentDao {


    @Override
    public Collection<Student> getAllStudents() {
        return new ArrayList<Student>(){
            {
                add(new Student(1, "Mario", "Nothing"));
            }
        };
    }

    @Override
    public Student getStudentById(int id) {
        return null;
    }

    @Override
    public void removeStudentById(int id) {

    }

    @Override
    public void updateStudent(Student student) {

    }

    @Override
    public void insertStudentToDb(Student student) {

    }
}
