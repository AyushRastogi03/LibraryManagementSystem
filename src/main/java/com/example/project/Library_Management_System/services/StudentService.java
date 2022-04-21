package com.example.project.Library_Management_System.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.Library_Management_System.models.Student;
import com.example.project.Library_Management_System.repositories.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentrepository ;

	public void createStudent(Student student) {
		studentrepository.save(student);	
	}
	
	public Student getStudentById(int id) {
		return studentrepository.findById(id).orElse(null);
	}
	
	public List<Student> getStudents(){
		return studentrepository.findAll() ;
	}
	

}

/*
 * Transaction Rollback
The @Transactional annotation is the metadata that specifies the semantics of the transactions on a method.
 We have two ways to rollback a transaction: declarative and programmatic.
In the declarative approach, we annotate the methods with the @Transactional annotation. The @Transactional 
annotation makes use of the attributes rollbackFor or rollbackForClassName to rollback the transactions, and the 
attributes noRollbackFor or noRollbackForClassName to avoid rollback on listed exceptions.
 *    
 *    
 */
