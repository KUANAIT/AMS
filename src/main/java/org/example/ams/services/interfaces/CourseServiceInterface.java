package org.example.ams.services.interfaces;

import org.example.ams.models.Course;

import java.util.List;

public interface CourseServiceInterface {
    List<Course> getAll();
    Course getById(int id);
    void save(Course course);
    void update(Course course);
    void delete(int id);
}
