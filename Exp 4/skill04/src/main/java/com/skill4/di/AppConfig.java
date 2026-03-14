package com.skill4.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // Annotation (Java-based) Configuration:
    // - Constructor Injection is used when creating the object
    // - Setter Injection is used to set/override course & year
    @Bean
    public Student student() {
        // constructor injection (all fields)
        Student s = new Student(202, "Yasaswini", "Computer Networks", 2);

        // setter injection (2 fields)
        s.setCourse("Hibernate + Spring Core");
        s.setYear(3);

        return s;
    }
}
