package com.example.project.Library_Management_System.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
public class Author {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id ; // reason we are selecting int id is it is fast in indexing 
	
	@Column(length = 30)
	private String name ;
	
	
	@Column(unique=true , nullable = false)
	private String email;

	@OneToMany(mappedBy = "myAuthor") // it tells which column it is refering to in book table // important for bidirectional relationship
	 @JsonIgnoreProperties(value = {"myAuthor", "createdOn", "updatedOn"})
	private List<Book> bookList ; // so we have use tightly coupled structure so if we call author api booklist will b created and if called 
	//book api author will be returned

  
	@CreationTimestamp
	private Date createdOn;

	@UpdateTimestamp
    private Date updatedOn;


}
