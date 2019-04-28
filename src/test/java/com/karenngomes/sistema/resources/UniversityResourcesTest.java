package com.karenngomes.sistema.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.karenngomes.resources.CourseResources;
import com.karenngomes.sistema.SystemApp;
import com.karenngomes.sistema.SystemConfig;
import com.karenngomes.sistema.model.Course;
import com.karenngomes.sistema.model.University;
import com.karenngomes.sistema.model.Secretary;
import com.karenngomes.sistema.model.University;
import com.karenngomes.sistema.utils.AcademicTypes;

import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

@ExtendWith(DropwizardExtensionsSupport.class)
public class UniversityResourcesTest {
	public static DropwizardAppExtension<SystemConfig> RULE = new DropwizardAppExtension<>(SystemApp.class,
			ResourceHelpers.resourceFilePath("config.yml"));
	public static Client client;

	@BeforeAll
	static void setup() {
		client = RULE.client();
	}

	@Test
	public void testCreateUniversity() {

		University university = new University("ufal");

		University universityJSON = client.target("http://localhost:8080/university").request()
				.post(Entity.json(university), University.class);

		assertNotNull(universityJSON, "Object is null");

	}

	@Test
	public void testGetAUniversity() {

		University university = new University("ufal");

		University universityJSON = client.target("http://localhost:8080/university").request()
				.post(Entity.json(university), University.class);

		University uJSON = client.target("http://localhost:8080/university/" + universityJSON.getId()).request()
				.get(University.class);

		assertEquals(universityJSON, uJSON);

	}

	@Test
	public void testUniversityNotFound() {
		Response uJSON = client.target("http://localhost:8080/university/138798273023").request().get();
		
		String message = uJSON.readEntity(String.class);

		assertEquals("{\"message\":\"University not found\"}", message);
	}

	
}