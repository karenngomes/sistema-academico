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
import com.karenngomes.sistema.model.Secretary;
import com.karenngomes.sistema.utils.AcademicTypes;

import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

@ExtendWith(DropwizardExtensionsSupport.class)
public class SecretaryResourcesTest {
	public static DropwizardAppExtension<SystemConfig> RULE = new DropwizardAppExtension<>(SystemApp.class,
			ResourceHelpers.resourceFilePath("config.yml"));
	public static Client client;

	AcademicTypes degreeLevelUnder = AcademicTypes.UNDERGRADUATE;

	@BeforeAll
	static void setup() {
		client = RULE.client();
	}

	@Test
	public void testCreateSecretary() {

		Secretary secretary = new Secretary("secretay 1", degreeLevelUnder);

		Secretary secretaryJSON = client.target("http://localhost:8080/secretary").request()
				.post(Entity.json(secretary), Secretary.class);

		assertNotNull(secretaryJSON, "Object is null");

	}

	@Test
	public void testGetASecretary() {

		Secretary secretary = new Secretary("secretay 1", degreeLevelUnder);

		Secretary secretaryJSON = client.target("http://localhost:8080/secretary").request()
				.post(Entity.json(secretary), Secretary.class);

		Secretary sJSON = client.target("http://localhost:8080/secretary/" + secretaryJSON.getId()).request()
				.get(Secretary.class);

		assertEquals(secretaryJSON, sJSON);

	}

	@Test
	public void testSecretaryNotFound() {
		Response sJSON = client.target("http://localhost:8080/secretary/22222944398").request().get();

		String message = sJSON.readEntity(String.class);

		assertEquals("{\"message\":\"Secretary not found\"}", message);
	}

	@Test
	public void testChangeType() {
		Secretary secretary = new Secretary("secretay 1", degreeLevelUnder);

		Secretary secretaryJSON = client.target("http://localhost:8080/secretary").request()
				.post(Entity.json(secretary), Secretary.class);

		secretary.setType(AcademicTypes.POSTGRADUATE);

		Response sJSON = client.target("http://localhost:8080/secretary/" + secretaryJSON.getId()).request()
				.put(Entity.json(secretary));

		String message = sJSON.readEntity(String.class);

		assertEquals("{\"message\":\"You can't change secretary type\"}", message);
	}
}
