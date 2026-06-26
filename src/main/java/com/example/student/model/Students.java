package com.example.student.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Schema(description = "Represents a student in the management system")
public class Students {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Unique identifier of the student", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
	private Long id;

	@Schema(description = "Full name of the student", example = "Ravi Kumar")
	private String name;

	@Schema(description = "Email address of the student", example = "ravi.kumar@example.com")
	private String email;

	@Schema(description = "Course enrolled by the student", example = "Computer Science")
	private String course;

}