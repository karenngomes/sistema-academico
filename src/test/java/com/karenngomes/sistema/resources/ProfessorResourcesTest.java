package com.karenngomes.sistema.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.karenngomes.sistema.SystemApp;
import com.karenngomes.sistema.SystemConfig;
import com.karenngomes.sistema.model.Professor;

import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

@ExtendWith(DropwizardExtensionsSupport.class)
public class ProfessorResourcesTest {
	public static DropwizardAppExtension<SystemConfig> RULE = new DropwizardAppExtension<>(SystemApp.class,
			ResourceHelpers.resourceFilePath("config.yml"));
	public static Client client;

	@BeforeAll
	static void setup() {
		client = RULE.client();
	}

	@Test
	public void testCreateProfessor() {

		Professor professor = new Professor("Willy", "Tiengo", 1234);

		Professor professorJSON = client.target("http://localhost:8080/professor").request()
				.post(Entity.json(professor), Professor.class);

		assertNotNull(professorJSON, "Object is null");

	}

	@Test
	public void testGetAProfessor() {

		Professor professor =new Professor("Willy", "Tiengo", 1234);

		Professor professorJSON = client.target("http://localhost:8080/professor").request()
				.post(Entity.json(professor), Professor.class);

		Professor pJSON = client.target("http://localhost:8080/professor/" + professorJSON.getId()).request()
				.get(Professor.class);

		assertEquals(professorJSON, pJSON);

	}

	@Test
	public void testProfessorNotFound() {
		Response pJSON = client.target("http://localhost:8080/professor/138798273023").request().get();
		
		String message = pJSON.readEntity(String.class);

		assertEquals("{\"message\":\"Professor not found\"}", message);
	}
}
