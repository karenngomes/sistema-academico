package com.karenngomes.sistema.db;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.karenngomes.sistema.model.Course;
import com.karenngomes.sistema.model.Department;
import com.karenngomes.sistema.model.Enrollment;
import com.karenngomes.sistema.model.Professor;
import com.karenngomes.sistema.model.Secretary;
import com.karenngomes.sistema.model.Student;
import com.karenngomes.sistema.model.Subject;
import com.karenngomes.sistema.model.University;

import io.dropwizard.testing.junit5.DAOTestExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import lombok.SneakyThrows;

@ExtendWith(DropwizardExtensionsSupport.class)
public class EnrollmentDAOTest {

	public DAOTestExtension dbTesting = DAOTestExtension.newBuilder().addEntityClass(University.class)
			.addEntityClass(Department.class).addEntityClass(Secretary.class).addEntityClass(Subject.class)
			.addEntityClass(Course.class).addEntityClass(Professor.class).build();

	private EnrollmentDAO dao;
	private static Validator validator;

	@BeforeClass
	@BeforeEach
	@SneakyThrows
	public void setUp() {
		System.out.println("setUp");
		dao = new EnrollmentDAO(dbTesting.getSessionFactory());
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
	public void testUniqueNumberValue() {
		
		Enrollment enrollment1 = new Enrollment(new Student("Karen", "Gomes"), 1);
		
		Enrollment enrollment2 = new Enrollment(new Student("Pedro", "Gomes"), 1);

		dbTesting.inTransaction(() -> dao.persist(enrollment1));
/*		Enrollment eToDB2 = dbTesting.inTransaction(() -> dao.persist(enrollment2));
		
		//
		System.out.println(eToDB2);

		/*
		Set<ConstraintViolation<Enrollment>> constraintViolations = validator.validate(enrollment2);
		
		assertEquals(1, constraintViolations.size());*/

		//assertThat( constraintViolations ).extracting( "message" ).containsOnly( "Must be add a University", "The field must be at least 2 characters" );
		//assertEquals("Must be add a University", constraintViolations.iterator().next().getMessage());
		//assertEquals("The field must be at least 2 characters", constraintViolations.iterator().next().getMessage());

	}
	
}
