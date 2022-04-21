package com.example.project.Library_Management_System.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.Library_Management_System.models.Student;

public interface StudentRepository extends JpaRepository<Student,Integer>{

}
