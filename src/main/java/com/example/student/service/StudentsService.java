package com.example.student.service;

import java.util.List;

import com.example.student.model.Students;

public interface StudentsService {
	Students saveStudent(Students student);

	List<Students> getAllStudents();

	Students getStudentById(Long id);

	Students updateStudent(Long id, Students student);

	void deleteStudent(Long id);

}
