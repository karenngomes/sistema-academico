package com.karenngomes.sistema.model;

// import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.*;
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

public class DepartmentTest {

	private static Validator validator;
	University university = new University("ufal");
	Secretary secretary = new Secretary("secretaria 1");

	@BeforeClass
	@BeforeEach
	@SneakyThrows
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testDepartmentNameAndUniversity() {

		Department department1 = new Department("t");

		Set<ConstraintViolation<Department>> constraintViolations = validator.validate(department1);
		
		assertEquals(2, constraintViolations.size());

		assertThat( constraintViolations ).extracting( "message" ).containsOnly( "Must be add a University", "The field must be at least 2 characters" );

	}
	
	@Test
	public void testDepartmentName() {

		Department department1 = new Department("t", university);

		Set<ConstraintViolation<Department>> constraintViolations = validator.validate(department1);
		
		assertEquals(1, constraintViolations.size());
		
		assertEquals("The field must be at least 2 characters", constraintViolations.iterator().next().getMessage());

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

		department.setUnderGraduate(secUndergraduate);

		assertThat(department, hasProperty("underGraduate"));

		assertThat(department, hasProperty("underGraduate", hasProperty("name", is("Undergraduate secretary"))));

	}

}
