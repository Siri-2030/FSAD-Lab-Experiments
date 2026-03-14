package com.skill4.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationMain {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        Student s = ctx.getBean(Student.class);

        System.out.println("=== SKILL-4: Spring DI (Annotations / Java Config) ===");
        System.out.println("Bean name: student");
        System.out.println(s);
        System.out.println("studentId = " + s.getStudentId());
        System.out.println("name      = " + s.getName());
        System.out.println("course    = " + s.getCourse());
        System.out.println("year      = " + s.getYear());

        ((AnnotationConfigApplicationContext) ctx).close();
    }
}
