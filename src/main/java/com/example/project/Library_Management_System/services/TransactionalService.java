package com.example.project.Library_Management_System.services;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.project.Library_Management_System.models.Book;
import com.example.project.Library_Management_System.models.Student;
import com.example.project.Library_Management_System.models.Transaction;
import com.example.project.Library_Management_System.models.TransactionStatus;
import com.example.project.Library_Management_System.models.TransactionType;
import com.example.project.Library_Management_System.repositories.TransactionalRepository;


@Service
public class TransactionalService {
	  
	@Autowired
	StudentService studentService ;
	
	@Value("${student.book.quota}")
	int studentBookQuota ;
	
	@Autowired
	BookService bookService ;
	
    @Value("${book.return.days}")
    int bookReturnDays;

    @Value("${book.fine.day}")
    int finePerDay;
	@Autowired
	TransactionalRepository transactionRepository ;
	
	
	public String issueBook(int studentId , int bookId) throws Exception {
		  Student student = studentService.getStudentById(studentId);
		 
		  if(student == null ) {
			  throw new Exception("Student is not present , unable to issue the book");
		  }
		   
		  if(student.getBookList().size()>=studentBookQuota) {
			  throw new Exception("Student has reached their quota , unable to issue the book");
		  }
		 
		  Book book = bookService.getBookById(bookId);
		  // also check if book == null
		  if(book.getStudent() != null) {
			  throw new Exception("book is already assigned to someone , unable t oissue the book");
		  }
		  
		  Transaction transaction = Transaction.builder()
				  .book(book)
				  .student(student)
				  .transactionType(TransactionType.ISSUE)
				  .transactionStatus(TransactionStatus.PENDING)
				  .transactionalId(UUID.randomUUID().toString())
				  .build() ;
		  
		  transactionRepository.save(transaction);
		  
		  try {
	            book.setStudent(student);
	            bookService.createBook(book); // This is going to update the existing book because bookId is not null

	            transaction.setTransactionStatus(TransactionStatus.SUCCESS);
	            transactionRepository.save(transaction);
	        }catch (Exception e){
	            book.setStudent(null);
	            bookService.createBook(book);

	            transaction.setTransactionStatus(TransactionStatus.FAILED);
	            transactionRepository.save(transaction);
	        }
		  
		return transaction.getTransactionalId();
	}
	
	public String returnBook(int studentId , int bookId) throws Exception {
		
		 /**
         * 1. Check whether the book is assigned to the student or not ?
         * 2. Check the fine and update in the transaction
         * 3. Create a txn with pending status
         * 4. Make the book available || Remove the student as assignee
         * 5. Update the txn with success status
         */
		
		Student student = studentService.getStudentById(studentId) ;
		
		Book book = bookService.getBookById(bookId);
		
		if(student==null || book == null || book.getStudent() == null 
				|| book.getStudent().getId()!= studentId) {
			throw new Exception("Book or student is either not present or book is not assigned " +
                    "to the student, unable to return the book");
		}
		
		List<Transaction> issueTxns = transactionRepository
				.findByBookAndStudentAndTransactionTypeOrderByIdDesc(book, student,TransactionType.ISSUE);
		
	   Transaction issueTxn = issueTxns.get(0);
	   
	   long issueTimeInMillis = issueTxn.getUpdatedOn().getTime();
	   long currentTimeInMillis = System.currentTimeMillis();
	   
	   long timeDiff = currentTimeInMillis - issueTimeInMillis ;
	   
	   long numberOfDaysPassed = TimeUnit.DAYS.convert(timeDiff , TimeUnit.MILLISECONDS);
	   
	   int fine = 0;
       if(numberOfDaysPassed > bookReturnDays){
           fine = (int)(numberOfDaysPassed - bookReturnDays) * finePerDay;
       }
		
       
       Transaction transaction = Transaction.builder()
    		   .transactionalId(UUID.randomUUID().toString())
    		   .transactionType(TransactionType.RETURN)
    		   .transactionStatus(TransactionStatus.PENDING)
    		   .student(student)
    		   .book(book)
    		   .fine(fine)
    		   .build();
       
       transactionRepository.save(transaction);
       
       try {
           book.setStudent(null);
           bookService.createBook(book);

           transaction.setTransactionStatus(TransactionStatus.SUCCESS);
           transactionRepository.save(transaction);
       }catch (Exception e){
           book.setStudent(student);
           bookService.createBook(book);

           transaction.setTransactionStatus(TransactionStatus.FAILED);
           transactionRepository.save(transaction);
       }
       
       
		return transaction.getTransactionalId(); 
	}

}

// above try catch we have used is the programmatical way of using rollback function , we can also use @Transactional for roll back of data 
// if any failure happen ..... 
