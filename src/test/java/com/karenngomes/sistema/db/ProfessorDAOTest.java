package com.karenngomes.sistema.db;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
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
public class ProfessorDAOTest {
	public DAOTestExtension dbTesting = DAOTestExtension.newBuilder().addEntityClass(University.class)
			.addEntityClass(Department.class).addEntityClass(Secretary.class).addEntityClass(Subject.class)
			.addEntityClass(Course.class).addEntityClass(Professor.class).build();

	private ProfessorDAO dao;
	private static Validator validator;


	@BeforeClass
	@BeforeEach
	@SneakyThrows
	public void setUp() {
		System.out.println("setUp");
		dao = new ProfessorDAO(dbTesting.getSessionFactory());
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testCreate() {

		Professor p1 = new Professor("Willy", "Tiengo", 123);
		

		Professor savedProf1 = dbTesting.inTransaction(() -> dao.persist(p1));

		assertNotNull(savedProf1);
		assertNotNull(savedProf1.getId());
		assertEquals(p1.getFirstName(), savedProf1.getFirstName());

	}

	@Test
	public void testGetById() {

		System.out.println("test find by id");

		Professor p1 = new Professor("Willy","Tiengo", 122);
		Professor p2 = new Professor("Marcio", "Ribeiro", 123);

		Professor savedProf1 = dbTesting.inTransaction(() -> dao.persist(p1));
		Professor savedProf2 = dbTesting.inTransaction(() -> dao.persist(p2));

		assertEquals(savedProf1.getId(), p1.getId());
		assertEquals(savedProf2, dao.get(p2.getId()));

	}

	@Test
	public void testFindAll() {

		System.out.println("test find all");

		Professor p1 = new Professor("Willy","Tiengo", 122);
		Professor p2 = new Professor("Marcio", "Ribeiro", 123);
		Professor p3 = new Professor("Rodrigo","Paes", 124);

		Professor savedProf1 = dbTesting.inTransaction(() -> dao.persist(p1));
		Professor savedProf2 = dbTesting.inTransaction(() -> dao.persist(p2));
		Professor savedProf3 = dbTesting.inTransaction(() -> dao.persist(p3));

		assertEquals(3, dao.findAll().size());

	}

}
