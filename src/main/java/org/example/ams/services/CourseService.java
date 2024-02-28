package org.example.ams.services;
import org.example.ams.models.Course;
import org.example.ams.repositories.CourseRepositoryInterface;
import org.example.ams.services.interfaces.CourseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService implements CourseServiceInterface {
    private final CourseRepositoryInterface repo;

    @Autowired
    public CourseService(CourseRepositoryInterface userRepository) {
        this.repo = userRepository;
    }

    @Override
    public List<Course> getAll() {
        return repo.findAll();
    }

    @Override
    public Course getById(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void save(Course course) {
        repo.save(course);
    }

    @Override
    public void update(Course course) {
        repo.save(course);
    }

    @Override
    public void delete(int id) {
        repo.deleteById(id);
    }
}