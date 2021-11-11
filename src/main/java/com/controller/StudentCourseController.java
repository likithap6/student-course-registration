package com.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Course;
import com.service.CourseService;
import com.entity.Student;
import com.service.StudentService;

@RestController
public class StudentCourseController {

	private StudentService studentService;
	private CourseService courseService;

	@Autowired
	public StudentCourseController(StudentService studentService, CourseService courseService) {
		this.studentService = studentService;
		this.courseService = courseService;
	}

	@PostMapping("/student")
	public String addStudent(@RequestBody Student student) {		
		studentService.addStudent(student);
		return "Student added successfully";
	}

	@DeleteMapping("/student/{studentId}")
	public String deleteStudent(String studentId) {
		studentService.deleteStudent(studentId);
		return "Student deleted successfully";
	}

	@PostMapping("/course")
	public String addCourse(@RequestBody Course course) {		
		courseService.addCourse(course);
		return "Course added successfully";
	}

	@PutMapping("/registerStudentsToCourse/{courseId}")
	public String registerStudentToCourse(@PathVariable String courseId, @RequestBody Set<Student> students) {
		courseService.registerStudentToCourse(courseId, students);
		return "Student registered to Course successfully ";
	}

	@GetMapping("/studentsByCourseName/{courseName}")
	public Set<Student> getStudentsByCourseName(@PathVariable String courseName) {
		return studentService.getStudentsByCourseName(courseName);
	}

}
