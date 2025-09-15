package com.StudentManagement.Dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

	private int studid;
	private Long fees;
	private String name;
	private String department;
	private String section;
	private String bloodgroup;
}
