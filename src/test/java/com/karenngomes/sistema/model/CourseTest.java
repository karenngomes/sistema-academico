package com.karenngomes.sistema.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lombok.SneakyThrows;

public class CourseTest {
	
	private static Validator validator;
	
	@BeforeClass
	@BeforeEach
	@SneakyThrows
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
	public void testAddCourseWithoutSecretary() {
		
		Course course1 = new Course();

		Set<ConstraintViolation<Course>> constraintViolations = validator.validate(course1);
		
		assertEquals(1, constraintViolations.size());

		assertEquals("Secretary can't be null", constraintViolations.iterator().next().getMessage());

		
	}
	
}
