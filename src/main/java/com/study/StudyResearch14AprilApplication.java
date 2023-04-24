package com.study;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StudyResearch14AprilApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyResearch14AprilApplication.class, args);
	}
	@Bean
    public ModelMapper modelmapper()
    {
    	return new ModelMapper();
    }
}
