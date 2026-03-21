package com.fsad.library.controller;

import java.util.*;

import org.springframework.web.bind.annotation.*;

import com.fsad.library.exception.StudentNotFoundException;
import com.fsad.library.model.Student;

@RestController
public class StudentController {

    List<Student> students = List.of(
            new Student(1, "Bhvuana", "AI&DS"),
            new Student(2, "Anita", "ECE"),
            new Student(3, "Rahul", "IT")
    );

    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable int id) {

        return students.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElseThrow(() -> new StudentNotFoundException("Student ID " + id + " not found"));
    }
}