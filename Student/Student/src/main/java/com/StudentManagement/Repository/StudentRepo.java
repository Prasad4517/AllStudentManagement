package com.StudentManagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.StudentManagement.Model.StudentEntity;

@Repository
public interface StudentRepo extends JpaRepository<StudentEntity,Integer> {

	@Query(value = "Select * from student_entity where Name=:name",nativeQuery=true)
	List<StudentEntity> studbyName(@Param("name") String name);
}
