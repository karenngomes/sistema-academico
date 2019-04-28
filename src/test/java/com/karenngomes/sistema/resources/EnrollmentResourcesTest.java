package com.karenngomes.sistema.resources;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.karenngomes.sistema.SystemApp;
import com.karenngomes.sistema.SystemConfig;
import com.karenngomes.sistema.model.Course;
import com.karenngomes.sistema.model.Enrollment;
import com.karenngomes.sistema.model.Secretary;
import com.karenngomes.sistema.model.Student;
import com.karenngomes.sistema.model.Subject;
import com.karenngomes.sistema.model.University;
import com.karenngomes.sistema.utils.AcademicTypes;

import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

@ExtendWith(DropwizardExtensionsSupport.class)
public class EnrollmentResourcesTest {
	public static DropwizardAppExtension<SystemConfig> RULE = new DropwizardAppExtension<>(SystemApp.class,
			ResourceHelpers.resourceFilePath("config.yml"));
	public static Client client;

	Student student = new Student("Karen", "Gomes", new Integer(10), new ArrayList<Subject>());
	AcademicTypes academicType = AcademicTypes.UNDERGRADUATE;

	Student studentJSON = client.target("http://localhost:8080/student").request().post(Entity.json(student),
			Student.class);
	Secretary secUndergraduate = client.target("http://localhost:8080/secretary").request()
			.post(Entity.json(new Secretary("Undergraduate secretary", academicType)), Secretary.class);

	Course course = new Course("course 1", academicType, secUndergraduate);

	Course courseJSON = client.target("http://localhost:8080/course").request().post(Entity.json(course), Course.class);

	@BeforeAll
	static void setup() {
		client = RULE.client();
	}

	@Test
	public void testCreateEnrollment() {
		Enrollment enrollment = new Enrollment(new Long(313), courseJSON, studentJSON);
		
		Enrollment enrollmentJSON = client.target("http://localhost:8080/enrollment").request()
				.post(Entity.json(enrollment), Enrollment.class);
		
		assertNotNull(enrollmentJSON, "Object is null");
	}

	@Test
	public void testAddNewSubject() {
		Enrollment enrollment = new Enrollment(new Long(326), courseJSON, studentJSON);
			
		Enrollment enrollmentJSON = client.target("http://localhost:8080/enrollment").request()
				.post(Entity.json(enrollment), Enrollment.class);
		Subject subjectJSON = client.target("http://localhost:8080/subject").request()
				.post(Entity.json(new Subject("subject 1","0898987", academicType, courseJSON)), Subject.class);
		
	
		Enrollment eNewSubjectJSON = client.target("http://localhost:8080/enrollment/" + enrollmentJSON.getId() + "/add_subject/" + subjectJSON.getId()).request()
				.post(Entity.json(null), Enrollment.class);
			
		assertEquals(eNewSubjectJSON.getCurrentSubjects(), new ArrayList<>(Arrays.asList(subjectJSON)));

	}

	@Test
	public void testEnrollmentNotFound() {
		Response eJSON = client.target("http://localhost:8080/enrollment/22222944398").request().get();

		String message = eJSON.readEntity(String.class);

		assertEquals("{\"message\":\"Enrollment not found\"}", message);
	}
}
