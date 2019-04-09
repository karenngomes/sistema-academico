package com.karenngomes.sistema.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import javax.ws.rs.client.Client;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.karenngomes.sistema.SystemApp;
import com.karenngomes.sistema.SystemConfig;
import com.karenngomes.sistema.model.Course;

import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

@ExtendWith(DropwizardExtensionsSupport.class)
public class ResourcesTest {
	public static DropwizardAppExtension<SystemConfig> RULE = new DropwizardAppExtension<>(SystemApp.class, ResourceHelpers.resourceFilePath("config.yml"));
	public static Client client;
	//final Course expected = new Course("Computer Science", new ArrayList()<>(), "graduate");
	// public static ArrayList<Course> expectedCourses = new ArrayList<>();
	
	@BeforeAll
	static void setup() {
		//client = RULE.client();
	}
	
	@Test
	public void corno() {
		assertEquals(1,1);
	}
}
