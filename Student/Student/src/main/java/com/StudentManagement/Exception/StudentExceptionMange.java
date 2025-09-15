package com.StudentManagement.Exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentExceptionMange {

	@ExceptionHandler(StudentOverflowExcpetions.class)
	public ResponseEntity<?> handleStudentOverflowException(StudentOverflowExcpetions ex){
		   return ResponseEntity
	                .status(HttpStatus.BAD_REQUEST)
	                .body(ex.getMessage());
	}
	
	@ExceptionHandler(InvalidStudentException.class)
	public ResponseEntity<?> handleInvaildStudentException(InvalidStudentException ex){
		   return ResponseEntity
	                .status(HttpStatus.BAD_REQUEST)
	                .body(ex.getMessage());
	}
}
