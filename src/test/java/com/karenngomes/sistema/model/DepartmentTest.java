package com.karenngomes.sistema.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

import com.karenngomes.sistema.utils.AcademicTypes;

public class DepartmentTest {

	@Test
	public void testDepartmentName() {

		Department department = new Department("IC");

		assertThat(department, hasProperty("name"));
		assertThat(department, hasProperty("name", equalTo("IC")));

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
