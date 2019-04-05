package com.karenngomes.sistema.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

import com.karenngomes.sistema.utils.AcademicTypes;

public class CourseTest {

	@Test
	public void testAddSubject() {
		AcademicTypes courseType = AcademicTypes.UNDERGRADUATE;
		AcademicTypes subjectType = AcademicTypes.UNDERGRADUATE;
		
		Secretary course = new Secretary("Undergraduate secretary", courseType);
		
		Course subject1 = new Course("Computer science", subjectType);
		Course subject2 = new Course("Computer engineering", AcademicTypes.POSTGRADUATE);
		/*
		course.setCourse(subject1);
		course.setCourse(subject2);
		
		assertThat(course.getCourses(), hasSize(1));
		*/
	}
}
