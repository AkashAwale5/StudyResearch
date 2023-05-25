package com.study;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class StudyResearch {

	public static void main(String[] args) {
		SpringApplication.run(StudyResearch.class, args);
	}
	@Bean
    public ModelMapper modelmapper()
    {
    	return new ModelMapper();
    }
}
