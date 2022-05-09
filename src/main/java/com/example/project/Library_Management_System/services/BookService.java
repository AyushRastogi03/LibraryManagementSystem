package com.example.project.Library_Management_System.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.Library_Management_System.models.Author;
import com.example.project.Library_Management_System.models.Book;
import com.example.project.Library_Management_System.models.Genre;
import com.example.project.Library_Management_System.models.Student;
import com.example.project.Library_Management_System.repositories.BookRepository;
import com.example.project.Library_Management_System.repositories.StudentRepository;
import com.example.project.Library_Management_System.responses.BookResponse;
import com.example.project.Library_Management_System.responses.BookWithoutStudentResponse;
import java.util.stream.Collectors;
@Service
public class BookService {  
	@Autowired
	BookRepository bookRepository ;
   
	@Autowired 
	AuthorService authorService;
	
	public void createBook(Book book) {
		
		Author autho = authorService.createOrGetAuthor(book.getMyAuthor());
		
		book.setMyAuthor(autho);
		
		bookRepository.save(book);
	}

	 public List<Book> getBooks(){
	        return bookRepository.findAll();
	    }
	
	 public Book getBookById(int id){
	        return bookRepository.findById(id).orElse(null);
	    }
	 
	 public List<Book> getBookByAuthorName(String name){
	        return bookRepository.findByMyAuthorName(name);
	    }

	    public List<Book> getBookByGenre(Genre genre){
	        return bookRepository.findByGenre(genre);
	    }
	    
	    public List<Book> getBookByName(String name){
	        return bookRepository.findByName(name);
	    }
	    
	    public List<Book> getBookById2(int id){
	        Optional<Book> bookOptional = bookRepository.findById(id);
	        if(bookOptional.isPresent()){
	            return Arrays.asList(bookOptional.get());
	        }

	        return new ArrayList<>();
	    }   
	    
	    public List<BookResponse> getBookByStudentName(String name){
	        List<Book> bookList = Arrays.asList(bookRepository.findByStudentName(name));

	        return bookList.stream()
	                .map(BookResponse::to)
	                .collect(Collectors.toList());
	    }

	    public List<BookWithoutStudentResponse> getBookByStudentNameWithoutStudent(String name){
	       List<Book> bookList = Arrays.asList(bookRepository.findByStudentName(name));
	        return bookList.stream()
	                .map(BookWithoutStudentResponse::to)
	                .collect(Collectors.toList());
	        
	    }
	    

//	    public List<BookResponse> getBookByStudentName(String name){
//	        List<Book> bookList = Arrays.asList(bookRepository.findByStudentName(name));
//
//	        return bookList.stream()
//	                .map(BookResponse::to)
//	                .collect(Collectors.toList());
//	    }
}
