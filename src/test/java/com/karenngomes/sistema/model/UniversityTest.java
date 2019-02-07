package com.karenngomes.sistema.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class UniversityTest {

	private static Validator validator;

	@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testUniversityNameSize() {

		//assertThrows(NullPointerException.class, () -> new University("k"));
		// assertThrows(IllegalArgumentException.class, () -> new University(""));

		University u1 = new University("kaaaa");

		Set<ConstraintViolation<University>> constraintViolations = validator.validate(u1);

		//assertEquals(1, constraintViolations.size());
		//assertEquals("Size must be between 4 and 50", constraintViolations.iterator().next().getMessage());

		// University u1 = new University("k");

		// todo: vai ser aceito com uma letra?

		// University u2 = new University("karen");

		// assertEquals("k", u1.getName());
	}

}
