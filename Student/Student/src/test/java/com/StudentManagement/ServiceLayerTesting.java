package com.StudentManagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.StudentManagement.Exception.InvalidStudentException;
import com.StudentManagement.Model.StudentEntity;
import com.StudentManagement.Repository.StudentRepo;
import com.StudentManagement.service.StudentService;

@ExtendWith(MockitoExtension.class)
public class ServiceLayerTesting {
 
	@Mock
	private StudentRepo repo;
	
	@InjectMocks 
	private StudentService service; 
	
	@Mock
	private ModelMapper modelMapper;
	
	@Test
	public void testsaveToDb() {
		 StudentEntity student = StudentEntity.builder()
	                .name("Prasad")
	                .department("CSE")
	                .Section("A")
	                .fees(25000L) 
	                .bloodgroup("A+")
	                .build();
		 
	        ResponseEntity<?> response = service.savetoDB(student);

	        verify(repo, times(1)).save(student);
	        assertEquals(HttpStatus.CREATED, response.getStatusCode());
	        assertEquals("Student registered successfully", response.getBody());
	}
	
	@Test
	public void testUpdateStudentSuccess() {
	    StudentEntity inputStudent = StudentEntity.builder()
	            .name("Prasad")
	            .department("CSE")
	            .Section("A")
	            .fees(25000L)
	            .bloodgroup("A+")
	            .build();

	    StudentEntity mappedStudent = StudentEntity.builder()
	            .name("Prasad")
	            .department("CSE")
	            .Section("A")
	            .fees(25000L)
	            .bloodgroup("A+")
	            .build();

	    int id = 4;

	    when(repo.count()).thenReturn(5L); // ID is valid
	    when(modelMapper.map(inputStudent, StudentEntity.class)).thenReturn(mappedStudent);

	    ResponseEntity<?> response = service.updateStudent(id, inputStudent);

	    verify(repo, times(1)).save(mappedStudent);
	    assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
	    assertEquals("Record updated successfully", response.getBody());
	}

	@Test
	public void testUpdateStudentInvalidId() {
	    StudentEntity student = StudentEntity.builder()
	            .name("Prasad")
	            .department("CSE")
	            .Section("A")
	            .fees(25000L)
	            .bloodgroup("A+")
	            .build();

	    int invalidId = 10;

	    when(repo.count()).thenReturn(5L); // ID is invalid

	    Exception ex = assertThrows(InvalidStudentException.class, () -> {
	        service.updateStudent(invalidId, student);
	    });

	    assertEquals("Student is invalid please enter valid details", ex.getMessage());
	}

	
	
}
