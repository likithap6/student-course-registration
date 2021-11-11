package com.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.StudentRepository;
import com.entity.Student;
import com.entity.Course;
import com.service.CourseService;

@Service
public class StudentService {
	
	private StudentRepository studentRepository;
	private CourseService courseService;
	private StudentService studentService;

	@Autowired
	public StudentService(CourseService courseService, StudentRepository studentRepository, StudentService studentService) {
		this.courseService = courseService;
		this.studentRepository = studentRepository;
		this.studentService = studentService;
	}
		
	public void addStudent(Student student) {
		student = studentRepository.save(student);		
	}
	
	//Alternate ways of writing addStudent
/*	public void addStudent(Student student) {
		students.add(student);
	}
*/	
	public void deleteStudent(String id) {
		Optional<Student> student = studentRepository.findById(Long.valueOf(id)); 		
		studentRepository.delete(student.get());
	}
	
	//Alternate ways of writing deleteStudent
/*	public void deleteStudent(String id) {
		for(Student s:students) {
			if(s.getId().equals(id)) {
				students.remove(s); 
			}
		}
		//return students.removeIf(t -> t.getId().equals(id));	This is better approach as its a lambda expression	
	}
*/	
	public void registerCourse(String id, Set<Course> courses) {
		Optional<Student> studentOptional = studentRepository.findById(Long.valueOf(id)); 
		
		Student student = studentOptional.get();
		courses.addAll(student.getCourses());
		student.setCourses(courses);
		studentRepository.save(student);
	}

	public Set<Student> getStudentsByCourseName(String courseName) {
		Optional<Course> course = courseService.getCourseByCourseName(courseName);
		
		Comparator<Student> studentOrderByName = (Student student1, Student student2) -> student1.getName()
				.compareTo(student2.getName());
		TreeSet<Student> sortedStudents = new TreeSet<>(studentOrderByName);

		Set<Student> students = course.get().getStudents();
		students.forEach(student -> student.setCourses(null));
		sortedStudents.addAll(students);		
		return sortedStudents;
	}
	
	/* Bonus points:
	 * Question 2.4 - What if we want to record course scores?  What possible changes need to be made? Explain briefly
	 * Answer - Create a TreeMap that stores the course & score for a particular student.
	 * 
	 * Question 2.5 - How to find all students who don’t register for a given course?
	 * Answer - Take the list of all student and minus the students who have this given course assigned.
	 * */
	
	
}
