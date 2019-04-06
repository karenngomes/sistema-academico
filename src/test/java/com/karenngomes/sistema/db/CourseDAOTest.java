package com.karenngomes.sistema.db;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
import com.karenngomes.sistema.utils.AcademicTypes;

import io.dropwizard.testing.junit5.DAOTestExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import lombok.SneakyThrows;

@ExtendWith(DropwizardExtensionsSupport.class)
public class CourseDAOTest {
	public DAOTestExtension dbTesting = DAOTestExtension.newBuilder().addEntityClass(University.class)
			.addEntityClass(Department.class).addEntityClass(Secretary.class).addEntityClass(Subject.class)
			.addEntityClass(Course.class).addEntityClass(Professor.class).build();

	private CourseDAO dao;

	@BeforeClass
	@BeforeEach
	@SneakyThrows
	public void setUp() {
		System.out.println("setUp");
		dao = new CourseDAO(dbTesting.getSessionFactory());
	}

	@Test
	public void testCreate() {

		AcademicTypes degreeLevel = AcademicTypes.UNDERGRADUATE;

		Course c1 = new Course("Computer science", degreeLevel);

		Course savedCourse1 = dbTesting.inTransaction(() -> dao.persist(c1));

		assertNotNull(savedCourse1);
		assertNotNull(savedCourse1.getId());
		assertEquals(c1.getName(), savedCourse1.getName());

	}

	@Test
	public void testGetById() {

		System.out.println("test find by id");

		AcademicTypes degreeLevel = AcademicTypes.UNDERGRADUATE;

		Course c1 = new Course("Computer science", degreeLevel);

		Course savedCourse1 = dbTesting.inTransaction(() -> dao.persist(c1));

		assertEquals(savedCourse1.getId(), c1.getId());

		assertEquals(savedCourse1, dao.getById(c1.getId()));

	}

	@Test
	public void testFindAll() {

		System.out.println("test find all");

		AcademicTypes degreeLevel = AcademicTypes.UNDERGRADUATE;

		Course c1 = new Course("Computer science", degreeLevel);
		Course c2 = new Course("Computer engineering", degreeLevel);
		Course c3 = new Course("Information Systems", degreeLevel);

		Course savedCourse1 = dbTesting.inTransaction(() -> dao.persist(c1));
		Course savedCourse2 = dbTesting.inTransaction(() -> dao.persist(c2));
		Course savedCourse3 = dbTesting.inTransaction(() -> dao.persist(c3));

		assertEquals(3, dao.findAll().size());

	}

	@Test
	public void testDeleteCourse() {

		AcademicTypes degreeLevel = AcademicTypes.UNDERGRADUATE;

		Course c1 = new Course("Computer science", degreeLevel);
		Course c2 = new Course("Computer engineering", degreeLevel);
		Course c3 = new Course("Information Systems", degreeLevel);

		Course savedCourse1 = dbTesting.inTransaction(() -> dao.persist(c1));
		Course savedCourse2 = dbTesting.inTransaction(() -> dao.persist(c2));
		Course savedCourse3 = dbTesting.inTransaction(() -> dao.persist(c3));

		assertEquals(3, dao.findAll().size());

		Course deletedDep3 = dao.delete(c3);

		assertEquals(2, dao.findAll().size());
		assertThat(dao.findAll(), not(hasItem(deletedDep3)));

	}
	
	@Test
	public void testDeleteCourse002() {
		AcademicTypes degreeLevel = AcademicTypes.UNDERGRADUATE;

		Course c1 = new Course("Computer science", degreeLevel);
		Course c2 = new Course("Computer engineering", degreeLevel);
		Course c3 = new Course("Information Systems", degreeLevel);

		Course savedCourse1 = dbTesting.inTransaction(() -> dao.persist(c1));
		Course savedCourse2 = dbTesting.inTransaction(() -> dao.persist(c2));
		Course savedCourse3 = dbTesting.inTransaction(() -> dao.persist(c3));
		
		dao.delete(c1);
		dao.delete(c2);
		dao.delete(c3);
		
		assertTrue(dao.findAll().isEmpty());
		assertNull(dao.getById(savedCourse1.getId()));
	}

}
