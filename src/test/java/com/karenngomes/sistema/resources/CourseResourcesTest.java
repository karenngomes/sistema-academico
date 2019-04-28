package com.karenngomes.sistema.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.karenngomes.sistema.SystemApp;
import com.karenngomes.sistema.SystemConfig;
import com.karenngomes.sistema.model.Course;
import com.karenngomes.sistema.model.Secretary;
import com.karenngomes.sistema.model.University;
import com.karenngomes.sistema.utils.AcademicTypes;

import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

@ExtendWith(DropwizardExtensionsSupport.class)
public class CourseResourcesTest {
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
	public void testGetACourse() {
		Course course1 = new Course("course 1", AcademicTypes.UNDERGRADUATE , secUndergraduate);
		
		Course courseJSON = client.target("http://localhost:8080/course").request()
				.post(Entity.json(course1), Course.class);
				
		Course cJSON = client.target("http://localhost:8080/course/" + courseJSON.getId()).request()
				.get(Course.class);
		
		assertEquals(cJSON, courseJSON);
		
	}
	
	@Test
	public void testCourseNotFound() {
		Response cJSON = client.target("http://localhost:8080/course/22222").request()
				.get();
		String message = cJSON.readEntity(String.class);
	
		assertEquals("{\"message\":\"Course not found\"}", message);
	}

	@Test
	public void testCourseAndSecretaryDiff() {
		Course department = new Course("course 1", AcademicTypes.POSTGRADUATE , secUndergraduate);
		
		Response departmentJSON = client.target("http://localhost:8080/course").request()
				.post(Entity.json(department));
		String message = departmentJSON.readEntity(String.class);

		assertEquals("{\"message\":\"Course and secretary aren't the same type\"}", message);

	}
	
	
}
