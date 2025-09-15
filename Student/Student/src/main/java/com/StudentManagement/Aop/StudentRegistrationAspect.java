package com.StudentManagement.Aop;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.StudentManagement.Exception.StudentOverflowExcpetions;
import com.StudentManagement.Model.StudentEntity;
import com.StudentManagement.Repository.StudentRepo;
import com.StudentManagement.service.StudentService;

@Aspect
@Component
public class StudentRegistrationAspect {
	
	@Autowired
	StudentRepo repo;	
	@Autowired
	StudentService service;

	@Before(" execution (* com.StudentManagement.service.StudentService.savetoDB(..))")
	public void checkingCurrentCount(JoinPoint joinPoint) {
		Object details = joinPoint.getThis();
        System.err.println("Intercepted class: " + details.getClass().getSimpleName());
		System.err.println("Executing AOP");
		List<StudentEntity> allStudents=service.findAllStudents();
		if(allStudents.size()>10) {
			throw new StudentOverflowExcpetions("Maximum limit reached you can not register");
//			System.err.println("We can not proceed to add student");
				}
	}
	
		
//		 @Around("execution(* com.StudentManagement.service.StudentService.savetoDB(..))")
//		    public Object restrictStudentRegistration(ProceedingJoinPoint joinPoint) throws Throwable {
//		        long count = repo.count();
//		        if (count >= 10) {
//		            System.err.println("Student limit reached. Blocking registration.");
//		            return ResponseEntity
//		                    .status(HttpStatus.BAD_REQUEST)
//		                    .body("Student registration limit reached. Cannot add more students.");
//		        }
//	
//		        return joinPoint.proceed(); // proceed with original method if condition passes
//		    }
		 
		 
		 
}
