package com.karenngomes.sistema.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import javax.ws.rs.client.Client;
//import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.karenngomes.sistema.SystemApp;
import com.karenngomes.sistema.SystemConfig;
import com.karenngomes.sistema.model.Course;
import com.karenngomes.sistema.model.Department;
import com.karenngomes.sistema.model.Secretary;
import com.karenngomes.sistema.model.University;
import com.karenngomes.sistema.utils.AcademicTypes;
import com.karenngomes.sistema.utils.ErrorMessage;

import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

@ExtendWith(DropwizardExtensionsSupport.class)

public class DepartmentResourcesTest {

	public static DropwizardAppExtension<SystemConfig> RULE = new DropwizardAppExtension<>(SystemApp.class,
			ResourceHelpers.resourceFilePath("config.yml"));
	public static Client client;

	AcademicTypes secretaryType = AcademicTypes.UNDERGRADUATE;

	Secretary secUndergraduate = client.target("http://localhost:8080/secretary").request()
			.post(Entity.json(new Secretary("Undergraduate secretary", secretaryType)), Secretary.class);

	University university = client.target("http://localhost:8080/university").request()
			.post(Entity.json(new University("ufal")), University.class);

	@BeforeAll
	static void setup() {
		client = RULE.client();
	}

	@Test
	public void testCreateDepartment() {

		Department department = new Department("IC", university);

		Department departmentJSON = client.target("http://localhost:8080/department").request()
				.post(Entity.json(department), Department.class);

		assertNotNull(departmentJSON, "Object is null");

	}

	@Test
	public void testGetADepartment() {

		Department department = new Department("IC", university);

		Department departmentJSON = client.target("http://localhost:8080/department").request()
				.post(Entity.json(department), Department.class);

		Department dJSON = client.target("http://localhost:8080/department/" + departmentJSON.getId()).request()
				.get(Department.class);

		assertEquals(departmentJSON, dJSON);

	}

	@Test
	public void testCourseNotFound() {
		Response dJSON = client.target("http://localhost:8080/department/8888888888").request().get();
		
		String message = dJSON.readEntity(String.class);

		assertEquals("{\"message\":\"Department not found\"}", message);
	}
}
