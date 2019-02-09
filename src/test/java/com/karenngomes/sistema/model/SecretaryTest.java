package com.karenngomes.sistema.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.karenngomes.sistema.utils.AcademicTypes;

import lombok.SneakyThrows;

public class SecretaryTest {

	private static Validator validator;

	@BeforeClass
	@BeforeEach
	@SneakyThrows
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
	public void testSetSecretaryName() {

		Secretary secretary = new Secretary("Undergraduate secretary", AcademicTypes.UNDERGRADUATE);
		String oldSecretaryName = secretary.getName();
		
		secretary.setName("Postgraduate secretary");
		
		assertThat(oldSecretaryName, is(not(equalTo(secretary.getName()))));
		
	}
	
	@Test
	public void testAddCourse() {
		AcademicTypes secretaryType = AcademicTypes.UNDERGRADUATE;
		AcademicTypes courseType = AcademicTypes.UNDERGRADUATE;
		
		Secretary secretary = new Secretary("Undergraduate secretary", secretaryType);
		
		Course course1 = new Course("Computer science", courseType);
		Course course2 = new Course("Computer engineering", AcademicTypes.POSTGRADUATE);
		
		secretary.setCourse(course1);
		secretary.setCourse(course2);
		
		assertThat(secretary.getCourses(), hasSize(1));
		assertThat(secretary.getCourses(), hasItem(course1));
		assertThat(secretary.getCourses(), hasItem(hasProperty("name", is("Computer science"))));
		
	}
	
}
