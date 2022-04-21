package com.example.project.Library_Management_System.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.Library_Management_System.services.TransactionalService;

@RestController
public class TransactionalController {
   
	@Autowired
	TransactionalService transactionalService ;
	
	@PostMapping("/transactional/issue")
	public String issueBook(@RequestParam("bookId") int bookId ,
			@RequestParam("studentId") int studentId) throws Exception {
		
		return transactionalService.issueBook(studentId, bookId);
	}
	
	
	@PostMapping("/transactional/return")
	public String returnBook(@RequestParam("bookId") int bookId ,
			@RequestParam("studentId") int studentId) throws Exception {
		
		return transactionalService.returnBook(studentId, bookId);
	}
}
