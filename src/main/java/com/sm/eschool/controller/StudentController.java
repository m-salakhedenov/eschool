package com.sm.eschool.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sm.eschool.mapper.StudentMapper;
import com.sm.eschool.model.Student;

@RestController
@RequestMapping("/api/students")
public class StudentController {
	
	private final StudentMapper studentMapper;

	public StudentController(StudentMapper studentMapper) {
		this.studentMapper = studentMapper;
	}
	
	@GetMapping
	public List<Student> getAllStudents() {
		return studentMapper.findAll();
	}
	
	@PostMapping
	public void addStudent(@RequestBody Student newStudent) {
		studentMapper.addStudent(newStudent);
	}
	
	@DeleteMapping("/{id}")
	public void deleteStudent(@PathVariable("id") Integer id) {
		if (id != null) {			
			studentMapper.deleteStudent(id);
		}
	}
	
}
