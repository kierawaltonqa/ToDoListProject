package com.qa.todolist.config;

import java.time.LocalTime;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	
	//check time every time app is run (just for my own validation)
	@Bean
	public String getTime() {
		return LocalTime.now().toString();
	}
	@Bean
	public ModelMapper getMapper() {
		return new ModelMapper();
	}

}
