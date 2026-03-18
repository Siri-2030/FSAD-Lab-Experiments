package com.fsad.library.service;

import java.util.*;
import org.springframework.stereotype.Service;
import com.fsad.library.model.Course;

@Service
public class CourseService {

    List<Course> courses = new ArrayList<>();

    public List<Course> getAllCourses() {
        return courses;
    }

    public Course addCourse(Course course) {
        courses.add(course);
        return course;
    }

    public Course getCourseById(int id) {
        for(Course c : courses) {
            if(c.getCourseId() == id)
                return c;
        }
        return null;
    }

    public Course updateCourse(int id, Course course) {
        for(Course c : courses) {
            if(c.getCourseId() == id) {
                c.setTitle(course.getTitle());
                c.setDuration(course.getDuration());
                c.setFee(course.getFee());
                return c;
            }
        }
        return null;
    }

    public boolean deleteCourse(int id) {
        return courses.removeIf(c -> c.getCourseId() == id);
    }

    public List<Course> searchCourse(String title) {
        List<Course> result = new ArrayList<>();

        for(Course c : courses) {
            if(c.getTitle().equalsIgnoreCase(title))
                result.add(c);
        }

        return result;
    }
}