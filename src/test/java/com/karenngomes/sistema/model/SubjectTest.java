package com.karenngomes.sistema.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;


import org.junit.jupiter.api.Test;

import com.karenngomes.sistema.utils.AcademicTypes;

public class SubjectTest {
	
	private static Course course = new Course("Computer Science", AcademicTypes.UNDERGRADUATE);
	
	@Test
	public void testSetSubjectName() {
	/*
		Subject subject = new Subject("Software testing", "1234", AcademicTypes.UNDERGRADUATE, course);
		String oldSubjectName = subject.getName();
		
		subject.setName("Data structures");
		
		assertThat(oldSubjectName, not(equalTo(subject.getName())));
		*/
	}
	
}
