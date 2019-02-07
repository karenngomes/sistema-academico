package com.karenngomes.sistema.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

import com.karenngomes.sistema.utils.AcademicTypes;

public class SecretaryTest {

	@Test
	public void testAddCourse() {
		AcademicTypes secretaryType = AcademicTypes.UNDERGRADUATE;
		AcademicTypes courseType = AcademicTypes.UNDERGRADUATE;
		
		Secretary secretary = new Secretary("Undergraduate secretary", secretaryType);
		
		Course course1 = new Course("Computer science", courseType);
		Course course2 = new Course("Computer engineering", AcademicTypes.POSTGRADUATE);
		
		secretary.setCourse(course1);
		secretary.setCourse(course2);
		
		assertThat(secretary.getCourses(), hasSize(2));
		
	}
	
}
