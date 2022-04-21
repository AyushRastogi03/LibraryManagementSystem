package com.example.project.Library_Management_System.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id ;
	
	private String name ;
	
	private int age ;
	
	@Column(unique = true, nullable = false)
	private String email ;
	
	 @Column(unique = true)
	private String phoneNumber ;
	
	@OneToMany(mappedBy = "student")// mappedBy is always current class' refernce in other class 
	@JsonIgnoreProperties(value= {"student" , "transactionList"})
	private List<Book> bookList;
	  
	 @OneToMany(mappedBy = "student")
	 @JsonIgnoreProperties(value = {"student", "book" })
    private List<Transaction> transactionList;
  
	@CreationTimestamp
	private Date createdOn;

	@UpdateTimestamp
    private Date updatedOn;
	
	
	

}
