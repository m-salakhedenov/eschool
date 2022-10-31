package com.sm.eschool.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sm.eschool.model.Student;

@Mapper
public interface StudentMapper {
	
	@Results(id = "studentResultMap", value = {
		@Result(property = "firstName", column = "first_name"),
		@Result(property = "lastName", column = "last_name"),
		@Result(property = "dateOfBirth", column = "date_of_birth")
	})
	@Select("SELECT * FROM students")
	List<Student> findAll();
	
	@ResultMap("studentResultMap")
	@Select("SELECT * FROM students WHERE id = #{studentId}")
	Student findById(@Param("studentId") int id);
	
	@Insert("INSERT INTO students (first_name, last_name, email, date_of_birth) VALUES (#{firstName}, #{lastName}, #{email}, #{dateOfBirth})")
	void addStudent(Student newStudent);
	
	@Update("UPDATE students SET first_name = #{firstName}, last_name = #{lastName}, email = #{email}, date_of_birth = #{dateOfBirth} WHERE id = #{id}")
	void updateStudent(Student student);
	
	@Delete("DELETE FROM students WHERE id = #{studentId}")
	void deleteStudent(@Param("studentId") int studentId);
	
}
