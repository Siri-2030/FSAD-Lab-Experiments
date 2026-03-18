package com.klu.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.klu.config.AppConfig;
import com.klu.model.Student;

public class MainApp {

	public static void main(String[] args) {
		ApplicationContext cn=new AnnotationConfigApplicationContext(AppConfig.class);
		Student or =cn.getBean(Student.class);
		or.display();
		ApplicationContext cn1=new AnnotationConfigApplicationContext(AppConfig.class);
		Student or1 =cn.getBean(Student.class);
		or.display();
		
	}

}
