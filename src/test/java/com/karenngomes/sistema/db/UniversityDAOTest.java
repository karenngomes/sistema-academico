package com.karenngomes.sistema.db;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
import com.karenngomes.sistema.model.Professor;
import com.karenngomes.sistema.model.Secretary;
import com.karenngomes.sistema.model.Subject;
import com.karenngomes.sistema.model.University;

import io.dropwizard.testing.junit5.DAOTestExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import lombok.SneakyThrows;

@ExtendWith(DropwizardExtensionsSupport.class)
public class UniversityDAOTest {

	public DAOTestExtension dbTesting = DAOTestExtension.newBuilder().addEntityClass(University.class)
			.addEntityClass(Department.class).addEntityClass(Secretary.class).addEntityClass(Subject.class)
			.addEntityClass(Course.class).addEntityClass(Professor.class).build();

	private UniversityDAO dao;
	private static Validator validator;

	@BeforeClass
	@BeforeEach
	@SneakyThrows
	public void setUp() {
		System.out.println("setUp");
		dao = new UniversityDAO(dbTesting.getSessionFactory());
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testCreate() {

		University u1 = new University("ufal1");

		University saved = dbTesting.inTransaction(() -> dao.persist(u1));

		assertNotNull(saved);
		assertNotNull(saved.getId());
		assertEquals(u1.getName(), saved.getName());

		// assertNotNull(null);
	}

	@Test
	public void testUniversityNameSize() {
		University u = new University("u1");

		Set<ConstraintViolation<University>> constraintViolations = validator.validate(u);

		assertEquals(1, constraintViolations.size());
		assertEquals("The field must be at least 4 characters", constraintViolations.iterator().next().getMessage());
	}

	/*
	@Test
	public void testUniversityNameSize002() {
		University u = new University("u1");
		University u2 = new University("u2adsjfhadsjbfajodbgajdgbaldfjgbajfhbgauyrhbandblandsbfajbdshfajsj");

		Set<ConstraintViolation<University>> constraintViolations = validator.validate(u2);
		constraintViolations = validator.validate(u);

		// assertEquals("The field must be less than 20 characters", constraintViolations.iterator().next().getMessage());
		
		assertThat(constraintViolations).extracting("message").containsOnly("The field must be at least 4 characters",
				"The field must be less than 20 characters");
		assertEquals(2, constraintViolations.size());

	}*/

	@Test
	public void testFindAll() {

		System.out.println("test find all");

		University u1 = new University("ufal1");
		University u2 = new University("ufal2");
		University u3 = new University("ufal3");

		University savedUniversity1 = dbTesting.inTransaction(() -> dao.persist(u1));
		University savedUniversity2 = dbTesting.inTransaction(() -> dao.persist(u2));
		University savedUniversity3 = dbTesting.inTransaction(() -> dao.persist(u3));

		System.out.println(dao.list());

		assertEquals(3, dao.list().size());

	}

	@Test
	public void testDeleteUniversity() {

		University u1 = new University("ufal1");
		University u2 = new University("ufal2");
		University u3 = new University("ufal3");

		University savedUniversity1 = dbTesting.inTransaction(() -> dao.persist(u1));
		University savedUniversity2 = dbTesting.inTransaction(() -> dao.persist(u2));
		University savedUniversity3 = dbTesting.inTransaction(() -> dao.persist(u3));

		dao.delete(u1);

		assertEquals(2, dao.list().size());
	}
}
