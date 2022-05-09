package com.example.project.Library_Management_System.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.Library_Management_System.models.Book;
import com.example.project.Library_Management_System.models.Genre;
import com.example.project.Library_Management_System.requests.BookCreateRequest;
import com.example.project.Library_Management_System.requests.BookFilterKey;
import com.example.project.Library_Management_System.responses.BookResponse;
import com.example.project.Library_Management_System.responses.BookWithoutStudentResponse;
import com.example.project.Library_Management_System.services.BookService;
@RestController
public class BookController { 
	// post, put, delete --> ADMIN
    // get --> STUDENT, ADMIN
      
	    @Autowired
	    BookService bookService ;
         
		@PostMapping("/book")
		public void createBook(@Valid @RequestBody BookCreateRequest bookcreatereq) {
			bookService.createBook(bookcreatereq.to());
		}
		
		@GetMapping("/book/all")
		public List<Book> getBooks(){
			return bookService.getBooks();
		}
		
		@GetMapping("/book")
	    public List<Book> getBook(@RequestParam("filterKey") String filterKey,
	                        @RequestParam("filterValue") String filterValue) throws Exception {

	        BookFilterKey bookFilterKey = BookFilterKey.valueOf(filterKey);

	        switch (bookFilterKey){
	            case AUTHOR_NAME:
	                return bookService.getBookByAuthorName(filterValue);
	            case BOOK_NAME:
	                return bookService.getBookByName(filterValue);
	            case BOOK_ID:
	                return bookService.getBookById2(Integer.parseInt(filterValue));
	            case GENRE:
	                return bookService.getBookByGenre(Genre.valueOf(filterValue));
	            default:
	                throw new Exception("Wrong filter type passed");
	        }
	    }
		
		  @GetMapping("/bookByStudent1")
		    public List<BookResponse> getBooksByStudent1(@RequestParam("name") String name) {
		        return bookService.getBookByStudentName(name);
		    }

		    @GetMapping("/bookByStudent2")
		    public List<BookWithoutStudentResponse> getBooks(@RequestParam("name") String name) {
		        return bookService.getBookByStudentNameWithoutStudent(name);
		    }
	

}
