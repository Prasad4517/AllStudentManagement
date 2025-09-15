package com.StudentManagement.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.StudentManagement.Dto.StudentDto;

@Configuration
public class config {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
