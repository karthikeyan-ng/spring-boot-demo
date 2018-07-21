package com.spring.boot.demo.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.spring.boot.demo.dao.StudentDao;
import com.spring.boot.demo.entity.Student;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
@Service
public class StudentService {

	@Autowired
	@Qualifier("fakeData")
	private StudentDao studentDao;

	public Collection<Student> getAllStudents() {
		return this.studentDao.getAllStudents();
	}

	public Student getStudentById(int id) {
		return this.studentDao.getStudentById(id);
	}

	public void removeStudentById(int id) {
		this.studentDao.removeStudentById(id);
	}

	public void updateStudent(Student student) {
		this.studentDao.updateStudent(student);
	}

	public void insertStudent(Student student) {
		this.studentDao.insertStudentToDb(student);
	}
}
