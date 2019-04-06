package com.karenngomes.sistema.db;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
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
public class SubjectDAOTest {
	public DAOTestExtension dbTesting = DAOTestExtension.newBuilder().addEntityClass(University.class)
			.addEntityClass(Department.class).addEntityClass(Secretary.class).addEntityClass(Subject.class)
			.addEntityClass(Course.class).addEntityClass(Professor.class).build();

	private SubjectDAO dao;

	@BeforeClass
	@BeforeEach
	@SneakyThrows
	public void setUp() {
		System.out.println("setUp");
		dao = new SubjectDAO(dbTesting.getSessionFactory());
	}

	@Test
	public void testCreate() {
		
		AcademicTypes degreeLevel = AcademicTypes.UNDERGRADUATE;

		Course course = new Course("Computer science", degreeLevel);
		assertEquals(course,course);
/*
		Subject s = new Subject("Software testing", "16215", degreeLevel, course);

		Subject savedSub = dbTesting.inTransaction(() -> dao.persist(s));
	
		assertNotNull(savedSub);
		assertNotNull(savedSub.getId());
		assertEquals(s.getName(), savedSub.getName());
*/
	}

	@Test
	public void testGetById() {

		System.out.println("test find by id");
		
		AcademicTypes degreeLevel = AcademicTypes.UNDERGRADUATE;

		Course c1 = new Course("Computer science", degreeLevel);
		Course c2 = new Course("Computer engineering", degreeLevel);
		Course c3 = new Course("Information Systems", degreeLevel);
/*
		Subject s1 = new Subject("Software testing", "16215", degreeLevel, c1);
		Subject s2 = new Subject("Software testing", "16215", degreeLevel, c2);
		Subject s3 = new Subject("Software testing", "16215", degreeLevel, c3);

		Subject savedSub1 = dbTesting.inTransaction(() -> dao.persist(s1));
		Subject savedSub2 = dbTesting.inTransaction(() -> dao.persist(s2));
		Subject savedSub3 = dbTesting.inTransaction(() -> dao.persist(s3));

		System.out.println(s1.getId());
		System.out.println(dao.getById(s1.getId()));
		System.out.println(savedSub1);

		assertEquals(savedSub1.getId(), s1.getId());

		assertEquals(savedSub1, dao.getById(s1.getId()));
*/
	}

	@Test
	public void testFindAll() {

		System.out.println("test find all");

		AcademicTypes degreeLevel = AcademicTypes.UNDERGRADUATE;

		Course c1 = new Course("Computer science", degreeLevel);
		Course c2 = new Course("Computer engineering", degreeLevel);
		Course c3 = new Course("Information Systems", degreeLevel);
/*
		Subject s1 = new Subject("Software testing", "16215", degreeLevel, c1);
		Subject s2 = new Subject("Software testing", "16215", degreeLevel, c2);
		Subject s3 = new Subject("Software testing", "16215", degreeLevel, c3);

		Subject savedSub1 = dbTesting.inTransaction(() -> dao.persist(s1));
		Subject savedSub2 = dbTesting.inTransaction(() -> dao.persist(s2));
		Subject savedSub3 = dbTesting.inTransaction(() -> dao.persist(s3));

		assertEquals(3, dao.findAll().size());
*/
	}

	@Test
	public void testDeleteSubject() {

		AcademicTypes degreeLevel = AcademicTypes.UNDERGRADUATE;

		Course c1 = new Course("Computer science", degreeLevel);
		Course c2 = new Course("Computer engineering", degreeLevel);
		Course c3 = new Course("Information Systems", degreeLevel);
/*
		Subject s1 = new Subject("Software testing", "16215", degreeLevel, c1);
		Subject s2 = new Subject("Software testing", "16215", degreeLevel, c2);
		Subject s3 = new Subject("Software testing", "16215", degreeLevel, c3);

		Subject savedSub1 = dbTesting.inTransaction(() -> dao.persist(s1));
		Subject savedSub2 = dbTesting.inTransaction(() -> dao.persist(s2));
		Subject savedSub3 = dbTesting.inTransaction(() -> dao.persist(s3));

		assertEquals(3, dao.findAll().size());

		Subject deletedSubs3 = dao.delete(s3);

		assertEquals(2, dao.findAll().size());
		assertThat(dao.findAll(), not(hasItem(deletedSubs3)));
*/
	}

}


