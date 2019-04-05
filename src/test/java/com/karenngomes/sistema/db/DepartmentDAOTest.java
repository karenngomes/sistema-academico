package com.karenngomes.sistema.db;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
public class DepartmentDAOTest {
	public DAOTestExtension dbTesting = DAOTestExtension.newBuilder().addEntityClass(Department.class)
			.addEntityClass(University.class).addEntityClass(Secretary.class).addEntityClass(Subject.class)
			.addEntityClass(Course.class).addEntityClass(Professor.class).build();

	private DepartmentDAO dao;
	private static Validator validator;

	@BeforeClass
	@BeforeEach
	@SneakyThrows
	public void setUp() {
		System.out.println("setUp");
		dao = new DepartmentDAO(dbTesting.getSessionFactory());
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
/*
	@Test
	public void testCreate() {

		//Department u1 = new Department("IC");

		//Department savedDep1 = dbTesting.inTransaction(() -> dao.persist(u1));

		//assertNotNull(savedDep1);
		//assertNotNull(savedDep1.getId());
		//assertEquals(u1.getName(), savedDep1.getName());

	}

	@Test
	public void testGetById() {

		System.out.println("test find by id");

		Department d1 = new Department("IC");
		Department d2 = new Department("IF");
		Department d3 = new Department("IM");

		Department savedDep1 = dbTesting.inTransaction(() -> dao.persist(d1));
		Department savedDep2 = dbTesting.inTransaction(() -> dao.persist(d2));
		Department savedDep3 = dbTesting.inTransaction(() -> dao.persist(d3));

		System.out.println(d1.getId());
		System.out.println(dao.getById(d1.getId()));
		System.out.println(savedDep1);

		assertEquals(savedDep1.getId(), d1.getId());

		assertEquals(savedDep1, dao.getById(d1.getId()));

	}

	@Test
	public void testFindAll() {

		System.out.println("test find all");
		Department d1 = new Department("IC");
		Department d2 = new Department("IF");
		Department d3 = new Department("IM");
		Department savedDep1 = dbTesting.inTransaction(() -> dao.persist(d1));
		Department savedDep2 = dbTesting.inTransaction(() -> dao.persist(d2));
		Department savedDep3 = dbTesting.inTransaction(() -> dao.persist(d3));

		assertEquals(3, dao.findAll().size());

	}

	@Test
	public void testDeleteDepartment() {

		Department d1 = new Department("IC");
		Department d2 = new Department("IF");
		Department d3 = new Department("IM");

		Department savedDep1 = dbTesting.inTransaction(() -> dao.persist(d1));
		Department savedDep2 = dbTesting.inTransaction(() -> dao.persist(d2));
		Department savedDep3 = dbTesting.inTransaction(() -> dao.persist(d3));

		assertEquals(3, dao.findAll().size());

		Department deletedDep3 = dao.delete(d3);

		assertEquals(2, dao.findAll().size());
		assertThat(dao.findAll(), not(hasItem(deletedDep3)));

	}
	*/
}
