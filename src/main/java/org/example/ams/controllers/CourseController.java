package org.example.ams.controllers;

import org.example.ams.models.Course;
import org.example.ams.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("hello")
    public String sayHello(){
        return "Hello World";
    }

    @GetMapping("/")
    public List<Course> getAll(){
        return courseService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getById(@PathVariable("id") int id){
        Course course = courseService.getById(id);
        if(course == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); //404

        return new ResponseEntity<>(course, HttpStatus.OK); //200
    }


    @PostMapping("/save")
    public ResponseEntity<Course> saveAndFindUser(@RequestBody Course course) {
        courseService.save(course);
        Course retrievedCourse = courseService.getById(course.getId());

        if (retrievedCourse != null) {
            if (course.getName().equals(retrievedCourse.getName()) &&
                    course.getDescription().equals(retrievedCourse.getDescription())){
                return new ResponseEntity<>(retrievedCourse, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Course> updateUser(@PathVariable int id, @RequestBody Course course) {
        courseService.update(course);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        courseService.delete(id);
        return new ResponseEntity<>("Deleted user with ID: " + id, HttpStatus.OK);
    }

}

