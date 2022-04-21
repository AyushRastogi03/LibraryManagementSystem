package com.example.project.Library_Management_System.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.example.project.Library_Management_System.models.Student;

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
public class StudentCreateRequest {
  
	@Positive
	private int age;
	
	@NotBlank
	private String name ;
	
	
	private String phoneNumber ;
	
	@NotBlank
	@Email
	private String email ;
	
	
	public Student to() {
		return Student.builder().name(name)
		.age(age)
		.email(email)
		.phoneNumber(phoneNumber)
		.build();
	}
	
}
