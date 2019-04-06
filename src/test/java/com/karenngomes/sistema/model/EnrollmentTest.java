package com.karenngomes.sistema.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.karenngomes.sistema.utils.AcademicTypes;

import lombok.SneakyThrows;

public class EnrollmentTest {
	private static Validator validator;
	University university = new University("ufal");
	AcademicTypes degreeLevel = AcademicTypes.UNDERGRADUATE;

	@BeforeClass
	@BeforeEach
	@SneakyThrows
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		//University university = new University("ufal");
	}
	/*
	@Test
	public void testStudentWithCreditsEnough() {
		Student student1 = new Student();
		student1.setCredits(10);
		Enrollment enrollment = new Enrollment(student1);
		Subject subject1 = new Subject();
		subject1.setCredits(20);
		subject1.setRequiredCredits(40);
		
		String result = enrollment.addSubject(subject1);
		
		assertEquals("SUCESS", result);

	}
	*/

	@Test
	public void testStudentWithoutCreditsEnough() {
		
		Student student = new Student();
		student.setCredits(30);
		Enrollment enrollment = new Enrollment(student, 1);
		Subject subject1 = new Subject("subject 1", "1234", degreeLevel);
		subject1.setCredits(20);
		subject1.setRequiredCredits(40);
		
		//String result = enrollment.addSubject(subject1);
		
		//assertEquals("STUDENT DOESN'T HAVE CREDITS ENOUGH", result);
		
	}
	
	/*
	@Test
	public void testaddSubject001() {
		Student student1 = new Student();
		student1.setCredits(40);
		Enrollment enrollment = new Enrollment(student1, 1l);
		Subject subject1 = new Subject("subject 1", "1234", degreeLevel);
		subject1.setCredits(20);
		subject1.setRequiredCredits(40);
		
		enrollment.addSubject(subject1);
		
		String result = enrollment.addSubject(subject1);
		
		assertEquals("CURRENT_SUBJECT", result);

	}
	*/
	
	/*
	@Test
	public void testAddACompleteSubject() {
		Student student1 = new Student();
		student1.setCredits(45);
		Enrollment enrollment = new Enrollment(student1);
		Subject subject1 = new Subject("subject 1", "1234", degreeLevel);
		subject1.setCredits(20);
		subject1.setRequiredCredits(40);
		
		enrollment.addSubject(subject1);
		
		
		
		Boolean result = completeSubjects(subject1);
		
		assertTrue(result);

	}
	*/
	/*
	@Test
	public void testAddACompleteSubject002() {
		Student student1 = new Student();
		student1.setCredits(30);
		Enrollment enrollment = new Enrollment(student1);
		Subject subject1 = new Subject();
		subject1.setCredits(20);
		subject1.setRequiredCredits(40);
		
		Subject subject2 = new Subject();
		
		enrollment.addSubject(subject1);
		
		
		
		Boolean result = completeSubjects(subject2);
		
		assertFalse(result);

	}
	*/
	
	/*
	@Test
	public void testaddSubject004() {
		Student student1 = new Student();
		student1.setCredits(30);
		Enrollment enrollment = new Enrollment(student1);
		Subject subject1 = new Subject();
		subject1.setCredits(20);
		subject1.setRequiredCredits(40);
		
		Subject subject2 = new Subject();
		
		enrollment.addSubject(subject1);
		
		completeSubjects(subject1);
		
		String result = enrollment.addSubject(subject1);
		
		assertEquals("COMPLETED_SUBJECT", result);

	}
	*/
	
	/*
	@Test
	public void testCurrentSubjects() {
		Student student1 = new Student();
		student1.setCredits(30);
		Enrollment enrollment = new Enrollment(student1);
		Subject subject1 = new Subject();
		subject1.setCredits(20);
		subject1.setRequiredCredits(40);
		
		Subject subject2 = new Subject();
		
		enrollment.addSubject(subject1);
		
		completeSubjects(subject1);
		
		String result = enrollment.addSubject(subject1);
		
		assertEquals("COMPLETED_SUBJECT", result);

	}
	*/

	/*
	@Test
	public void testDepartmentUniversity() {

		Department department1 = new Department(university);
		//department1.setName("d");

		Set<ConstraintViolation<Department>> constraintViolations = validator.validate(department1);
		
		assertEquals(1, constraintViolations.size());

		//assertThat( constraintViolations ).extracting( "message" ).containsOnly( "Must be add a University", "The field must be at least 2 characters" );
		//assertEquals("Must be add a University", constraintViolations.iterator().next().getMessage());
		//assertEquals("The field must be at least 2 characters", constraintViolations.iterator().next().getMessage());

	}
*/
}
