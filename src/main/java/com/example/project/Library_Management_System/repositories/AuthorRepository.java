package com.example.project.Library_Management_System.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.project.Library_Management_System.models.Author;

public interface AuthorRepository extends JpaRepository<Author,Integer> { // <Name of tableName or Class , DataType Of primaryKey>
  
	
	@Query(value = "select a from Author a where a.email = :email")
	Author findAuthor(String email); // check piyush code explained JPQL and Native Query 

    /** Custom queries
     * 1. JPQL - Java persistence query language (format which executes query considering java classes / objects)
     * 2. Native query - Format which executes query considering your sql tables / relations

     * :<arg_name> --> Refer by the arg name
     * ?<arg_number> --> Refer by arg number
     * 
     * 
     //    @Query(value = "select a from Author a where a.name = :name and a.email = ?2")
//    Author findAuthorByNameAndEmail(String name, String email);
//
//    @Query(value = "select * from author where author_id = ?1", nativeQuery = true)
//    Author findAuthorById2(int id);

     */
}
