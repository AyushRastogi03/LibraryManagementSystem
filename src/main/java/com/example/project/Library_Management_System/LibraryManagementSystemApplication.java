package com.example.project.Library_Management_System;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.project.Library_Management_System.security.User;
import com.example.project.Library_Management_System.security.UserService;

@SpringBootApplication
public class LibraryManagementSystemApplication implements CommandLineRunner{
	
	@Autowired
	UserService userService;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementSystemApplication.class, args);
	}
    
	
	@Override
	public void run(String... args) throws Exception { // this we have used to create a Admin then we have commented so that there cant be duplicate value in User table or we can make create Admin api as well 

//		User user = User.builder()
//				.username("sagar@gmail.com")
//				.password(passwordEncoder.encode("sagar123"))
//				.authority("adm")
//				.build();
//
//		userService.createUser(user);
	}
}
