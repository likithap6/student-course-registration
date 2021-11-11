package com.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
	public Optional<Course> findCourseByName(String courseName);
}
