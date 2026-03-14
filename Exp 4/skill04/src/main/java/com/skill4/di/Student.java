package com.skill4.di;

public class Student {
    private int studentId;
    private String name;
    private String course;
    private int year;

    // Task-2: Constructor Injection (all fields)
    public Student(int studentId, String name, String course, int year) {
        this.studentId = studentId;
        this.name = name;
        this.course = course;
        this.year = year;
    }

    // Task-3: Setter methods (at least two fields)
    public void setCourse(String course) {
        this.course = course;
    }

    public void setYear(int year) {
        this.year = year;
    }

    // getters (optional but useful)
    public int getStudentId() { return studentId; }
    public String getName() { return name; }
    public String getCourse() { return course; }
    public int getYear() { return year; }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", course='" + course + '\'' +
                ", year=" + year +
                '}';
    }
}
