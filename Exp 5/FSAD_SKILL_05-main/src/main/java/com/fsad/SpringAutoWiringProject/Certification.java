package com.fsad.SpringAutoWiringProject;

import org.springframework.stereotype.Component;

@Component
public class Certification {

    private int id = 101;
    private String name = "Java Programming";
    private String dateOfCompletion = "10-03-2026";

    public void displayCertification() {
        System.out.println("Certification ID: " + id);
        System.out.println("Certification Name: " + name);
        System.out.println("Date of Completion: " + dateOfCompletion);
    }
}