package com.example.project.Library_Management_System.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.project.Library_Management_System.models.Author;

public interface AuthorRepository extends JpaRepository<Author,Integer> { // <Name of tableName or Class , DataType Of primaryKey>
  
	
	@Query(value = "select a from Author a where a.email = :email")
	Author findAuthor(String email); // check piyush code explained JPQL and Native Query 
}
