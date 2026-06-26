package com.example.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.student.model.Students;

public interface StudentRepository extends JpaRepository<Students, Long> {

}
