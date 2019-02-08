package com.karenngomes.sistema.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
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

public class DepartmentTest {

	private static Validator validator;

	@BeforeClass
	@BeforeEach
	@SneakyThrows
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testDepartmentName() {

		Department department1 = new Department("I");
		Department department2 = new Department("");

		Set<ConstraintViolation<Department>> constraintViolations = validator.validate(department1);
		// constraintViolations = validator.validate(department2);

		assertEquals(1, constraintViolations.size());
		assertEquals("The field must be at least 2 characters", constraintViolations.iterator().next().getMessage());

	}
	
	@Test
	public void testDepartmentName002() {

		Department department = new Department(null);

		Set<ConstraintViolation<Department>> constraintViolations = validator.validate(department);

		assertEquals(1, constraintViolations.size());
		assertEquals("may not be null", constraintViolations.iterator().next().getMessage());

	}

	@Test
	public void testDepartmentName003() {

		Department department = new Department("IC");

		assertThat(department, hasProperty("name"));
		assertThat(department, hasProperty("name", equalTo("IC")));

	}
	
	@Test
	public void testSetDepartmentName() {

		Department department = new Department("IC");
		String oldDepartmentName = department.getName();
		
		department.setName("IM");
		
		assertThat(oldDepartmentName, is(not(equalTo(department.getName()))));
		
	}

	@Test
	public void testSecretaryType() {

		AcademicTypes secretaryType = AcademicTypes.UNDERGRADUATE;

		Secretary secUndergraduate = new Secretary("Undergraduate secretary", secretaryType);

		Department department = new Department("IC");

		department.setSecretary(secUndergraduate);

		assertThat(department, hasProperty("underGraduate"));

		assertThat(department, hasProperty("underGraduate", hasProperty("name", is("Undergraduate secretary"))));

	}

}
