package com.sm.eschool;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sm.eschool.mapper.StudentMapper;
import com.sm.eschool.model.Student;

@SpringBootTest
public class EschoolApplicationTests extends AbstractTestNGSpringContextTests {

	@Autowired
	private StudentMapper studentMapper;
	
	@Test
	public void contextLoads() {
		Assert.assertNotNull(studentMapper);
	}
	
	@Test
	public void findStudentsTest() {
		Assert.assertTrue(studentMapper.findAll().size() > 0);
	}
	
	@Test
	public void addAndDeleteStudentTest() {
		List<Student> students = studentMapper.findAll();
		studentMapper.addStudent(new Student(0, "test", "test", "test", LocalDate.now()));
		Assert.assertTrue(students.size() < studentMapper.findAll().size());
		studentMapper.deleteStudent(3);
		Assert.assertTrue(students.size() == studentMapper.findAll().size());
	}
	
	@Test
	public void updateStudentTest() {
		Student byIdOne = studentMapper.findById(1);
		byIdOne.setEmail("test");
		studentMapper.updateStudent(byIdOne);
		Assert.assertTrue("test".equals(studentMapper.findById(1).getEmail()));
	}

}
