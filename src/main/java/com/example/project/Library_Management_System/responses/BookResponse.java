package com.example.project.Library_Management_System.responses;

import com.example.project.Library_Management_System.models.Book;
import com.example.project.Library_Management_System.models.Genre;
import com.example.project.Library_Management_System.models.Student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponse {

    private int id;
    private String name;
    private Genre genre;

    private Student student;

    public static BookResponse to(Book book){
        return BookResponse.builder()
                .id(book.getId())
                .name(book.getName())
                .genre(book.getGenre())
                .student(book.getStudent())
                .build();
    }
}
