package com.StudentManagement.Exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

public class StudentOverflowExcpetions extends RuntimeException {
             
	public StudentOverflowExcpetions(String msg) {
		super(msg);
	}
}
