package com.StudentManagement.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.StudentManagement.Exception.InvalidStudentException;
import com.StudentManagement.Model.StudentEntity;
import com.StudentManagement.Repository.StudentRepo;

@Service
public class StudentService {
    
	@Autowired
	StudentRepo repo;
	
	@Autowired
	ModelMapper modelMapper;
	
	public ResponseEntity<?> savetoDB(StudentEntity studentity) {
	    System.err.println("Post value: " + studentity);
	    repo.save(studentity);
	    return ResponseEntity.status(HttpStatus.CREATED)
	                         .body("Student registered successfully");
	}

	public List<StudentEntity> findAllStudents() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	public ResponseEntity<?> updateStudent(int id, StudentEntity studentity) {
		 if(id>repo.count()) {
			 throw new InvalidStudentException("Student is invalid please enter valid details");
		 }
		 
	   StudentEntity student=modelMapper.map(studentity,StudentEntity.class);
	   repo.save(student);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Record updated successfully");
	}

	public List<StudentEntity> getStudentByName(String name) {
		// TODO Auto-generated method stub
		List<StudentEntity> stud=repo.studbyName(name);
		return stud;
	}
}