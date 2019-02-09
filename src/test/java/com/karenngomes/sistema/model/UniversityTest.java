package com.karenngomes.sistema.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lombok.SneakyThrows;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class UniversityTest {

	private static Validator validator;

	@BeforeClass
	@BeforeEach
	@SneakyThrows
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testUniversityNameSize() {
		University u1 = new University("u1");

		Set<ConstraintViolation<University>> constraintViolations = validator.validate(u1);

		assertEquals(1, constraintViolations.size());
		assertEquals("The field must be at least 4 characters", constraintViolations.iterator().next().getMessage());

	}
	
	
	@Test
	public void testUniversityNameSize002() {
		University u = new University("u2adsjfhadsjbfajodbgajdgbaldfjgbajfhbgauyrhbandblandsbfajbdshfajsj");

		Set<ConstraintViolation<University>> constraintViolations = validator.validate(u);
	
		assertEquals("The field must be less than 20 characters", constraintViolations.iterator().next().getMessage());
			

	}

}
