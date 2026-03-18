package com.fsad.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import com.fsad.library.model.Course;
import com.fsad.library.service.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    CourseService service;

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses(){
        return new ResponseEntity<>(service.getAllCourses(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Course> addCourse(@RequestBody Course course){
        return new ResponseEntity<>(service.addCourse(course), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourse(@PathVariable int id){

        Course c = service.getCourseById(id);

        if(c != null)
            return new ResponseEntity<>(c,HttpStatus.OK);
        else
            return new ResponseEntity<>("Course Not Found",HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable int id,@RequestBody Course course){

        Course c = service.updateCourse(id,course);

        if(c!=null)
            return new ResponseEntity<>(c,HttpStatus.OK);
        else
            return new ResponseEntity<>("Course Not Found",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable int id){

        boolean deleted = service.deleteCourse(id);

        if(deleted)
            return new ResponseEntity<>("Course Deleted",HttpStatus.OK);
        else
            return new ResponseEntity<>("Course Not Found",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/search/{title}")
    public ResponseEntity<List<Course>> search(@PathVariable String title){
        return new ResponseEntity<>(service.searchCourse(title),HttpStatus.OK);
    }
}