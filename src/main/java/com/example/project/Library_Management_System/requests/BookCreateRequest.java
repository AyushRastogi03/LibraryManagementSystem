package com.example.project.Library_Management_System.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.example.project.Library_Management_System.models.Author;
import com.example.project.Library_Management_System.models.Book;
import com.example.project.Library_Management_System.models.Genre;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookCreateRequest {
    
	// book details
	 
	@NotBlank
	private String name ;
	
	@NotNull
	private Genre genre ;
	
	
	// author details
	@NotBlank
	private  String authorName ;
	@Email
	@NotBlank
	private String email;
	
	
	public Book to() {
		
		Author author = Author.builder()
				.name(authorName)
				.email(email)
				.build() ;
		
		return Book.builder()
				.name(name)
				.genre(genre)
				.myAuthor(
						author
						)
				.build();
	}
	
}
