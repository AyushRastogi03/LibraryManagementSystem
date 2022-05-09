package com.example.project.Library_Management_System.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.Library_Management_System.models.Student;
import com.example.project.Library_Management_System.requests.StudentCreateRequest;
import com.example.project.Library_Management_System.security.User;
import com.example.project.Library_Management_System.services.StudentService;

@RestController
public class StudentController {

	@Autowired
	StudentService studentService;
	
	//permit all 
	@PostMapping("/student")
	public void createStudent(@Valid @RequestBody StudentCreateRequest studentcreatereq) {
		studentService.createStudent(studentcreatereq.to());
	}
	
	//admin
	@GetMapping("/student/all")
	public List<Student> getStudents(){
		return studentService.getStudents();
	}
	
	//admin
	@GetMapping("/student/{studentid}")
	public Student getStudentById(@PathVariable("studentid") int id) {
		
		return studentService.getStudentById(id);
	}
	
	 // Student
    @GetMapping("/student")
    public Student getStudent(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer studentId = user.getStudent().getId();
        return studentService.getStudentById(studentId);
    }

}
