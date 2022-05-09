package com.example.project.Library_Management_System.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.Library_Management_System.security.User;
import com.example.project.Library_Management_System.services.TransactionalService;

@RestController
public class TransactionalController {
   
	@Autowired
	TransactionalService transactionalService ;
	
	@PostMapping("/transactional/issue")
	public String issueBook(@RequestParam("bookId") int bookId
			) throws Exception {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer studentId = user.getStudent().getId();
		return transactionalService.issueBook(studentId, bookId);
	}
	
	
	@PostMapping("/transactional/return")
	public String returnBook(@RequestParam("bookId") int bookId
			) throws Exception {
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer studentId = user.getStudent().getId();
		return transactionalService.returnBook(studentId, bookId);
	}
}
