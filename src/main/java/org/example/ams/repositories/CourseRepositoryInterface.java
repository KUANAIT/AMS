package org.example.ams.repositories;

import org.example.ams.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepositoryInterface extends JpaRepository<Course, Integer> {
}

