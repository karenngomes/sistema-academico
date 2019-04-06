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
	Secretary secretary = new Secretary("secretaria 1");

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

		Course c1 = new Course("Computer science", degreeLevel, secretary);

		Course savedCourse1 = dbTesting.inTransaction(() -> dao.persist(c1));

		assertNotNull(savedCourse1);
		assertNotNull(savedCourse1.getId());
		assertEquals(c1.getName(), savedCourse1.getName());

	}

	@Test
	public void testGetById() {

		System.out.println("test find by id");

		AcademicTypes degreeLevel = AcademicTypes.UNDERGRADUATE;

		Course c1 = new Course("Computer science", degreeLevel, secretary);

		Course savedCourse1 = dbTesting.inTransaction(() -> dao.persist(c1));

		assertEquals(savedCourse1.getId(), c1.getId());

		assertEquals(savedCourse1, dao.get(c1.getId()));

	}

	@Test
	public void testFindAll() {

		System.out.println("test find all");

		AcademicTypes degreeLevel = AcademicTypes.UNDERGRADUATE;

		Course c1 = new Course("Computer science", degreeLevel, secretary);
		Course c2 = new Course("Computer engineering", degreeLevel, secretary);
		Course c3 = new Course("Information Systems", degreeLevel, secretary);

		Course savedCourse1 = dbTesting.inTransaction(() -> dao.persist(c1));
		Course savedCourse2 = dbTesting.inTransaction(() -> dao.persist(c2));
		Course savedCourse3 = dbTesting.inTransaction(() -> dao.persist(c3));

		assertEquals(3, dao.findAll().size());

	}


}
