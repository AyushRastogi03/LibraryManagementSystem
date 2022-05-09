package com.example.project.Library_Management_System.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.example.project.Library_Management_System.models.Student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.project.Library_Management_System.security.*;
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
	
    @NotBlank
    @Size(min = 8, max = 14)
    private String password;
	
	public Student to() {
		return Student.builder().name(name)
		.age(age)
		.email(email)
		.phoneNumber(phoneNumber)
		.user(
                User.builder()
                        .username(this.email)
                        .password(this.password)
                        .build()
        )
        .build();
	}
	
}
