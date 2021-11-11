package com.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CourseRepository;
import com.entity.Course;
import com.entity.Student;

@Service
public class CourseService {
	
	private CourseRepository courseRepository;
	private CourseService courseService;
		
	/*private List<Course> students = new ArrayList<Course>(Arrays.asList(
			new Course("1","StudentOne"),
			new Course("2","StudentTwo"),
			new Course("3","StudentThree")
			)); */
	
	@Autowired
	public CourseService(CourseRepository courseRepository, CourseService courseService) {
		this.courseRepository = courseRepository;
		this.courseService = courseService;
	}

	public void addCourse(Course course) {
		course = courseRepository.save(course);
		//return course.getId();
	}

	public void registerStudentToCourse(String courseId, Set<Student> students) {
		
		Optional<Course> courseOptional = courseRepository.findById(Long.valueOf(courseId)); 
		
		Course course = courseOptional.get();
		students.addAll(course.getStudents());
		course.setStudents(students);
		courseRepository.save(course);
	}
	
	public Optional<Course> getCourseByCourseName(String courseName) {
		return courseRepository.findCourseByName(courseName);
	}
}
