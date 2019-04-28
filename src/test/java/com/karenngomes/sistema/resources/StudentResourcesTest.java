package com.karenngomes.sistema.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.karenngomes.sistema.SystemApp;
import com.karenngomes.sistema.SystemConfig;
import com.karenngomes.sistema.model.Student;
import com.karenngomes.sistema.model.Subject;

import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

@ExtendWith(DropwizardExtensionsSupport.class)
public class StudentResourcesTest {
	public static DropwizardAppExtension<SystemConfig> RULE = new DropwizardAppExtension<>(SystemApp.class,
			ResourceHelpers.resourceFilePath("config.yml"));
	public static Client client;

	@BeforeAll
	static void setup() {
		client = RULE.client();
	}

	@Test
	public void testCreateStudent() {

		Student student = new Student("Karen", "Gomes", new Integer(10), new ArrayList<Subject>());

		Student studentJSON = client.target("http://localhost:8080/student").request()
				.post(Entity.json(student), Student.class);

		assertNotNull(studentJSON, "Object is null");

	}

	@Test
	public void testGetAStudent() {

		Student student = new Student("Nich", "Araujo", new Integer(15), new ArrayList<Subject>());

		Student studentJSON = client.target("http://localhost:8080/student").request()
				.post(Entity.json(student), Student.class);

		Student sJSON = client.target("http://localhost:8080/student/" + studentJSON.getId()).request()
				.get(Student.class);

		assertEquals(studentJSON.getId(), sJSON.getId());

	}

	@Test
	public void testStudentNotFound() {
		Response sJSON = client.target("http://localhost:8080/student/1387023").request().get();
		
		String message = sJSON.readEntity(String.class);

		assertEquals("{\"message\":\"Student not found\"}", message);
	}
}
