package com.example.project.Library_Management_System.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class Transaction {  
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id ;
	
	private String transactionalId;
	
	@ManyToOne
	@JoinColumn
	@JsonIgnoreProperties(value= {"transactionList" , "bookList" })
	private Student student ;
	
	@Enumerated(value = EnumType.STRING)
	private TransactionType transactionType;
	
	@Enumerated(value = EnumType.STRING)
	private TransactionStatus transactionStatus ;
	
	private Integer fine ;
	
	
	@ManyToOne
	@JoinColumn
	@JsonIgnoreProperties(value = {"transactionList" , "student"})
	private Book book ;
	
	@CreationTimestamp
	private Date createdOn;
	
	@UpdateTimestamp
	private Date updatedOn;

}
