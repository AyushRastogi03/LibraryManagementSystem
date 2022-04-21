package com.example.project.Library_Management_System.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.Library_Management_System.models.Book;
import com.example.project.Library_Management_System.models.Student;
import com.example.project.Library_Management_System.models.Transaction;
import com.example.project.Library_Management_System.models.TransactionType;

public interface TransactionalRepository extends 
  JpaRepository<Transaction , Integer>{

	
	List<Transaction> findByBookAndStudentAndTransactionTypeOrderByIdDesc(
			Book book , Student student , TransactionType transactionType );
}
