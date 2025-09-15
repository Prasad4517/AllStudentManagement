package com.StudentManagement.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.StudentManagement.Dto.StudentDto;
import com.StudentManagement.Exception.InvalidStudentException;
import com.StudentManagement.Model.StudentEntity;
import com.StudentManagement.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private StudentService service;
	
	 @PostMapping("/register")
	    public ResponseEntity<?> registerStudent(@RequestBody StudentDto userDto) {
	     System.err.println("Hello inside the post request"); 
		 StudentEntity studentity=modelMapper.map(userDto, StudentEntity.class);
	        return service.savetoDB(studentity);

	    }
	 
	 
	 @GetMapping("/all")
	 public ResponseEntity<List<StudentDto>> getAllStudents() {
	     List<StudentEntity> students = service.findAllStudents();
	     
          List<StudentDto> studDtosresult=students.stream()
                                     .map(student->modelMapper.map(student,StudentDto.class)) 
        		                                        .collect(Collectors.toList());
          int index=0;
          for(StudentDto i:studDtosresult) {
        	 System.err.println( ++index + "value "+i);
        	 }
          return ResponseEntity.ok(studDtosresult);
	 }
	 
	 @PutMapping("/put/{id}")
	 public ResponseEntity<?> updateStudent(@PathVariable int id,@RequestBody StudentDto dto){
		 System.err.println("request arrived to putmapping");
		 StudentEntity stuentity=modelMapper.map(dto,StudentEntity.class);
		 return service.updateStudent(id,stuentity);
	 }
	 
	 @GetMapping("/name/{name}")
	 public ResponseEntity<?> getStudentByName(@PathVariable String name) {
		 System.err.println("Request reched to getmapping by name");
		 List<StudentEntity> StudentbyName= service.getStudentByName(name);
		 List<StudentDto> StudentDtoByName=StudentbyName.stream()
				                            .map(student->modelMapper.map(student,StudentDto.class))
				                           .collect(Collectors.toList());

		 if(StudentbyName.isEmpty()) {
			 throw new InvalidStudentException("User Not Exist Of Given Data");
//			  return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                      .body("No student found with name: " + name);	
			  };
		 return ResponseEntity.ok(StudentDtoByName);	
	 }
}
