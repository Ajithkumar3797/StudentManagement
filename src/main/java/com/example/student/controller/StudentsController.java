package com.example.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.student.model.Students;
import com.example.student.service.StudentsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/students")
@Tag(name = "Student Management", description = "APIs for managing students")
public class StudentsController {

	@Autowired
	private StudentsService service;

	@Operation(summary = "Create a new student", description = "Adds a new student record to the system")
	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "Student created successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Students.class))),
			@ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content) })

	@PostMapping
	public ResponseEntity<Students> createStudent(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Student object to be created", required = true, content = @Content(schema = @Schema(implementation = Students.class))) @RequestBody Students student) {
		Students saved = service.saveStudent(student);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);

	}

	@Operation(summary = "Get all students", description = "Retrieves a list of all students in the system")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Students retrieved successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = Students.class)))) })

	@GetMapping
	public ResponseEntity<List<Students>> getStudents() {
		return ResponseEntity.ok(service.getAllStudents());
	}

	@Operation(summary = "Get a student by ID", description = "Retrieves a single student record by their unique ID")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Student found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Students.class))),
			@ApiResponse(responseCode = "404", description = "Student not found", content = @Content) })

	@GetMapping("/{id}")
	public ResponseEntity<Students> getStudent(
			@Parameter(name = "id", description = "ID of the student to retrieve", required = true, in = ParameterIn.PATH, schema = @Schema(type = "integer", format = "int64", example = "1")) @PathVariable("id") Long id) {
		Students student = service.getStudentById(id);
		if (student == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(student);

	}

	@Operation(summary = "Update an existing student", description = "Updates the name, email, and course of an existing student record identified by their unique ID.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Student updated successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Students.class))),
			@ApiResponse(responseCode = "400", description = "Invalid request body", content = @Content),
			@ApiResponse(responseCode = "404", description = "Student not found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })

	@PutMapping("/{id}")
	public ResponseEntity<Students> updateStudent(
			@Parameter(name = "id", description = "ID of the student to update", required = true, in = ParameterIn.PATH, schema = @Schema(type = "integer", format = "int64", example = "1")) @PathVariable("id") Long id,
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Updated student details (id field is ignored)", required = true, content = @Content(schema = @Schema(implementation = Students.class))) @RequestBody Students student) {
		Students updated = service.updateStudent(id, student);
		if (updated == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updated);

	}

	@Operation(summary = "Delete a student", description = "Removes a student record from the system by their unique ID")
	@ApiResponses({
			@ApiResponse(responseCode = "204", description = "Student deleted successfully", content = @Content),
			@ApiResponse(responseCode = "404", description = "Student not found", content = @Content) })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteStudent(
			@Parameter(name = "id", description = "ID of the student to delete", required = true, in = ParameterIn.PATH, schema = @Schema(type = "integer", format = "int64", example = "1")) @PathVariable("id") Long id) {
		service.deleteStudent(id);
		return ResponseEntity.noContent().build();

	}
}
