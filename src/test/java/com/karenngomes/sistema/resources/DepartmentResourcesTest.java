package com.karenngomes.sistema.resources;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.karenngomes.resources.CourseResources;
import com.karenngomes.sistema.SystemApp;
import com.karenngomes.sistema.SystemConfig;
import com.karenngomes.sistema.model.Course;
import com.karenngomes.sistema.model.Department;

import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@ExtendWith(DropwizardExtensionsSupport.class)
public class DepartmentResourcesTest {

	public static DropwizardAppExtension<SystemConfig> RULE = new DropwizardAppExtension<>(SystemApp.class, ResourceHelpers.resourceFilePath("config.yml"));
	public static Client client;
	//public static Secretary graduate, postgraduate;
	//public static Department expectedWithGraduate, expectedWithoutSecretary;
	//public static ArrayList<Subject> expectedSubjects = new ArrayList<>();
	//public static ArrayList<Department> expectedDepartments = new ArrayList<>();
	
	@BeforeAll
	static void setup() {
		System.out.println(RULE);
		client = RULE.client();
	}
	
	@Test
	public void createDeparment()  {
		/*
		URI uri = new URI("http://localhost:8080/department");
		WebTarget target = client.target(uri);
		Department d  = target.request().get(Department.class);
		System.out.println(d);
		*/
		System.out.println("chega aqui pelo meno");
		CourseResources course = client.target(
				String.format("http://localhost:%d/course/5", RULE.getLocalPort()))
				.request().get(CourseResources.class);
		
		System.out.println(course);
		
	}
	
	
	
}
