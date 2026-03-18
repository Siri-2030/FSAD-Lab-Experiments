package com.klu.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Student {

    @Value("50")
    private int studentId;

    @Value("K Bhuvana Siri")
    private String name;

    @Value("Artificial Intelligence")
    private String course;

    @Value("2")
    private int year;

    // No-arg constructor
    public Student() {
    }

    // Setter methods
    public void setCourse(String course) {
        this.course = course;
    }

    public void setYear(int year) {
        this.year = year;
    }

    // Display method instead of toString
    public void display() {
        System.out.println("Student ID   : " + studentId);
        System.out.println("Name         : " + name);
        System.out.println("Course       : " + course);
        System.out.println("Year         : " + year);
    }
}
