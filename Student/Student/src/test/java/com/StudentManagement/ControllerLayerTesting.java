package com.StudentManagement;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.StudentManagement.Controller.StudentController;
import com.StudentManagement.Dto.StudentDto;
import com.StudentManagement.Model.StudentEntity;
import com.StudentManagement.service.StudentService;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(StudentController.class)
public class ControllerLayerTesting {

	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService service;

    @MockBean
    private ModelMapper modelMapper;

    @Test
    public void testGetAllStudents() throws Exception {
        StudentEntity student1 = new StudentEntity(1, 25000L, "Prasad", "CSE", "A", "A+");
        StudentEntity student2 = new StudentEntity(2, 30000L, "Ravi", "ECE", "B", "B+");
        List<StudentEntity> studentEntities = Arrays.asList(student1, student2);

        StudentDto dto1 = new StudentDto(1, 25000L, "Prasad", "CSE", "A", "A+");
        StudentDto dto2 = new StudentDto(2, 30000L, "Ravi", "ECE", "B", "B+");

        when(service.findAllStudents()).thenReturn(studentEntities);
        when(modelMapper.map(student1, StudentDto.class)).thenReturn(dto1);
        when(modelMapper.map(student2, StudentDto.class)).thenReturn(dto2);

        mockMvc.perform(get("/students/all"))  // 👈 replace with actual mapping like "/students"
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.size()").value(2))
               .andExpect(jsonPath("$[0].name").value("Prasad"))
               .andExpect(jsonPath("$[1].name").value("Ravi"));
    }
 }
