package com.example.project.Library_Management_System.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.Library_Management_System.models.Author;
import com.example.project.Library_Management_System.repositories.AuthorRepository;

@Service
public class AuthorService {
	
	@Autowired
	AuthorRepository authorRepository ;
	
	public Author createOrGetAuthor(Author auth) {
		
		Author authorFromDB = authorRepository.findAuthor(auth.getEmail());
		
		if(authorFromDB == null)
			authorFromDB  = authorRepository.save(auth);
		
		return authorFromDB ;
	}
 
}
