package com.example.student.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.student.model.Students;
import com.example.student.repository.StudentRepository;
import com.example.student.service.StudentsService;

@Service
public class StudentsServiceImpl implements StudentsService {

	@Autowired
	private StudentRepository repository;

	public Students saveStudent(Students student) {
		return repository.save(student);
	}

	public List<Students> getAllStudents() {
		return repository.findAll();
	}

	public Students getStudentById(Long id) {
		return repository.findById(id).orElse(null);
	}

	public Students updateStudent(Long id, Students updatedStudent) {
		return repository.findById(id).map(existing -> {
			existing.setName(updatedStudent.getName());
			existing.setEmail(updatedStudent.getEmail());
			existing.setCourse(updatedStudent.getCourse());
			return repository.save(existing);
		}).orElse(null);
	}

	public void deleteStudent(Long id) {
		repository.deleteById(id);
	}

}
