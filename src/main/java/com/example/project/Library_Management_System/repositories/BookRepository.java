package com.example.project.Library_Management_System.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.project.Library_Management_System.models.Book;
import com.example.project.Library_Management_System.models.Genre;

public interface BookRepository  extends JpaRepository<Book,Integer> {
  
//  @Query("select b from Book b where b.myAuthor.name = ?1")
  List<Book> findByMyAuthorName(String authorName);

//  @Query("select b from Book b where b.name = :bookName")
  List<Book> findByName(String bookName);

//  @Query("select b from Book b where b.genre = :genre")
  List<Book> findByGenre(Genre genre);

  @Query("select b from Book b where b.student.name = ?1")
  Book findByStudentName(String name);
}
