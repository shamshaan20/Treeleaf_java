package com.diwash.ManyToMany.Controller;
import java.util.ArrayList;
import java.util.List;

import com.diwash.ManyToMany.Models.Course;
import com.diwash.ManyToMany.exception.ResourceNotFoundException;
import com.diwash.ManyToMany.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class CourseController {
    @Autowired
    CourseRepository courseRepository;
    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllCourses(@RequestParam(required = false) String title) {
        List<Course> course = new ArrayList<Course>();
        if (title == null)
            courseRepository.findAll().forEach(course::add);
        else
            courseRepository.findByTitleContaining(title).forEach(course::add);
        if (course.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(course, HttpStatus.OK);
    }
    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable("id") long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Course with id = " + id));
        return new ResponseEntity<>(course, HttpStatus.OK);
    }
    @PostMapping("/courses")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course _course = courseRepository.save(new Course(course.getTitle(), course.getDescription(), true));
        return new ResponseEntity<>(_course, HttpStatus.CREATED);
    }
    @PutMapping("/courses/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable("id") long id, @RequestBody Course course) {
        Course _course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Course with id = " + id));
        _course.setTitle(course.getTitle());
        _course.setDescription(course.getDescription());
        _course.setPublished(course.isPublished());

        return new ResponseEntity<>(courseRepository.save(_course), HttpStatus.OK);
    }
    @DeleteMapping("/courses/{id}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable("id") long id) {
        courseRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/courses")
    public ResponseEntity<HttpStatus> deleteAllCourses() {
        courseRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/courses/published")
    public ResponseEntity<List<Course>> findByPublished() {
        List<Course> courses = courseRepository.findByPublished(true);
        if (courses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(courses, HttpStatus.OK);
    }
}