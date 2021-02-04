package com.qa.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ToDoListProjectApplication {

	public static void main(String[] args) {
		
		ApplicationContext beanBag = SpringApplication.run(ToDoListProjectApplication.class, args);
		
		//print out local time when system starts up
		System.out.println("Server Time:\t" + beanBag.getBean("getTime",String.class));
	}

}
