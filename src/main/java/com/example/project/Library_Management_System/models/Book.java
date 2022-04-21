package com.example.project.Library_Management_System.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // https://thorben-janssen.com/jpa-generate-primary-keys/
	private int id ;
	
	private String name ;
	
	
	@Enumerated(value=EnumType.STRING)// to store this enum in the sql as above values will be stored as 
	 // table columns so to store genre as table column we use this annotation (it has two value types - String and 
	// odinal , odinal is basically numeric form)
	private Genre genre ;
	
	@ManyToOne
	@JoinColumn  //@JoinColumn is used to specify a column for joining an entity association or element collection.
	//this represent Book Id as foreign key in author table
	@JsonIgnoreProperties(value = "bookList")
	private Author myAuthor ;
	
	@ManyToOne
	@JoinColumn
	@JsonIgnoreProperties(value= {"bookList" , "transactionList"})
	private Student student ;
	
    @OneToMany(mappedBy = "book")
    @JsonIgnoreProperties(value = {"book" , "student"})
    private List<Transaction> transactionList;
	
	@CreationTimestamp
	private Date createdOn ;
	
	@UpdateTimestamp
	private Date updatedOn ;
	
	
	
	
	
}
