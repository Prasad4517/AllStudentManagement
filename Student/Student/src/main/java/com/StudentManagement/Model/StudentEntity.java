package com.StudentManagement.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int studid;
	private Long fees;
	private String name;
	private String department;
	private String Section;
	private String bloodgroup;
	
}
