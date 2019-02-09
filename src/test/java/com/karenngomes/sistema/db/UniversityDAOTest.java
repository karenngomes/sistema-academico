package com.karenngomes.sistema.db;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.hamcrest.Matchers.*;

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

	@BeforeClass
	@BeforeEach
	@SneakyThrows
	public void setUp() {
		System.out.println("setUp");
		dao = new UniversityDAO(dbTesting.getSessionFactory());
	}

	@Test
	public void testCreate() {

		University u1 = new University("ufal1");

		University savedUni1 = dbTesting.inTransaction(() -> dao.persist(u1));

		assertNotNull(savedUni1);
		assertNotNull(savedUni1.getId());
		assertEquals(u1.getName(), savedUni1.getName());

	}

	@Test
	public void testGetById() {

		System.out.println("test find by id");

		University u1 = new University("ufal1");
		University u2 = new University("ufal2");
		University u3 = new University("ufal3");

		University savedUniversity1 = dbTesting.inTransaction(() -> dao.persist(u1));
		University savedUniversity2 = dbTesting.inTransaction(() -> dao.persist(u2));
		University savedUniversity3 = dbTesting.inTransaction(() -> dao.persist(u3));

		System.out.println(u1.getId());
		System.out.println(dao.getById(u1.getId()));
		System.out.println(savedUniversity1);

		assertEquals(savedUniversity1.getId(), u1.getId());

		assertEquals(savedUniversity1, dao.getById(u1.getId()));

	}

	@Test
	public void testFindAll() {

		System.out.println("test find all");

		University u1 = new University("ufal1");
		University u2 = new University("ufal2");
		University u3 = new University("ufal3");
		
		University savedUniversity1 = dbTesting.inTransaction(() -> dao.persist(u1));
		University savedUniversity2 = dbTesting.inTransaction(() -> dao.persist(u2));
		University savedUniversity3 = dbTesting.inTransaction(() -> dao.persist(u3));

		assertEquals(3, dao.findAll().size());

	}

	@Test
	public void testDeleteUniversity() {

		University u1 = new University("ufal1");
		University u2 = new University("ufal2");
		University u3 = new University("ufal3");

		University savedUniversity1 = dbTesting.inTransaction(() -> dao.persist(u1));
		University savedUniversity2 = dbTesting.inTransaction(() -> dao.persist(u2));
		University savedUniversity3 = dbTesting.inTransaction(() -> dao.persist(u3));

		assertEquals(3, dao.findAll().size());

		University deletedUniversity3 = dao.delete(u3);

		assertEquals(2, dao.findAll().size());
		assertThat(dao.findAll(), not(hasItem(deletedUniversity3)));

	}
}
