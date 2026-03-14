package com.skill4.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlMain {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student s = (Student) ctx.getBean("student");

        System.out.println("=== SKILL-4: Spring DI (XML) ===");
        System.out.println("Bean name: student");
        System.out.println(s);
        System.out.println("studentId = " + s.getStudentId());
        System.out.println("name      = " + s.getName());
        System.out.println("course    = " + s.getCourse());
        System.out.println("year      = " + s.getYear());

        ((ClassPathXmlApplicationContext) ctx).close();
    }
}
