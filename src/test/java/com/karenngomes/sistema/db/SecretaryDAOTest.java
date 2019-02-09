package com.karenngomes.sistema.db;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
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
import com.karenngomes.sistema.utils.AcademicTypes;

import io.dropwizard.testing.junit5.DAOTestExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import lombok.SneakyThrows;

@ExtendWith(DropwizardExtensionsSupport.class)
public class SecretaryDAOTest {
	public DAOTestExtension dbTesting = DAOTestExtension.newBuilder().addEntityClass(University.class)
			.addEntityClass(Department.class).addEntityClass(Secretary.class).addEntityClass(Subject.class)
			.addEntityClass(Course.class).addEntityClass(Professor.class).build();

	private SecretaryDAO dao;

	@BeforeClass
	@BeforeEach
	@SneakyThrows
	public void setUp() {
		System.out.println("setUp");
		dao = new SecretaryDAO(dbTesting.getSessionFactory());
	}
	
	@Test
	public void testEquals() {
		
		AcademicTypes degreeLevel = AcademicTypes.UNDERGRADUATE;

		Secretary secretary = new Secretary("Undergraduate secretary", degreeLevel);

		Course course1 = new Course("Computer science", degreeLevel);
		Course course2 = new Course("Computer engineering", degreeLevel);

		secretary.setCourse(course1);
		secretary.setCourse(course2);

		dao.persist(secretary);

		Secretary secretary2 = secretary;

		Course course3 = new Course("Information Systems", degreeLevel);
		
		secretary2.setCourse(course3);
		
		dao.persist(secretary2);
		
		assertTrue(secretary.equals(secretary2));
		
	}
	
	@Test
	public void testCreate() {
		
		AcademicTypes secretaryType = AcademicTypes.UNDERGRADUATE;
		AcademicTypes courseType = AcademicTypes.UNDERGRADUATE;
		
		Secretary secretary = new Secretary("Undergraduate secretary", secretaryType);
		
		Course course1 = new Course("Computer science", courseType);
		Course course2 = new Course("Computer engineering", AcademicTypes.POSTGRADUATE);
		
		secretary.setCourse(course1);
		secretary.setCourse(course2);
		
		Secretary savedSecretary = dbTesting.inTransaction(() -> dao.persist(secretary));

		assertNotNull(savedSecretary);
		assertNotNull(savedSecretary.getId());
		assertEquals(secretary.getName(), savedSecretary.getName());

	}

	@Test
	public void testGetById() {

		System.out.println("test find by id");
		
		AcademicTypes degreeLevel = AcademicTypes.UNDERGRADUATE;
		
		Secretary s1 = new Secretary("Undergraduate secretary", degreeLevel);
		Secretary s2 = new Secretary("Undergraduate secretary", degreeLevel);
		Secretary s3 = new Secretary("Undergraduate secretary", degreeLevel);
		
		Secretary savedSecretary1 = dbTesting.inTransaction(() -> dao.persist(s1));
		Secretary savedSecretary2 = dbTesting.inTransaction(() -> dao.persist(s2));
		Secretary savedSecretary3 = dbTesting.inTransaction(() -> dao.persist(s3));

		System.out.println(s1.getId());
		System.out.println(dao.getById(s1.getId()));
		System.out.println(savedSecretary1);

		assertEquals(savedSecretary1.getId(), s1.getId());

		assertEquals(savedSecretary1, dao.getById(s1.getId()));

	}

	@Test
	public void testFindAll() {

		System.out.println("test find all");

		AcademicTypes degreeLevel = AcademicTypes.UNDERGRADUATE;
		
		Secretary s1 = new Secretary("Undergraduate secretary", degreeLevel);
		Secretary s2 = new Secretary("Undergraduate secretary", degreeLevel);
		Secretary s3 = new Secretary("Undergraduate secretary", degreeLevel);
		
		Secretary savedSecretary1 = dbTesting.inTransaction(() -> dao.persist(s1));
		Secretary savedSecretary2 = dbTesting.inTransaction(() -> dao.persist(s2));
		Secretary savedSecretary3 = dbTesting.inTransaction(() -> dao.persist(s3));

		assertEquals(3, dao.findAll().size());

	}

	@Test
	public void testDeleteSecretary() {

		AcademicTypes degreeLevel = AcademicTypes.UNDERGRADUATE;

		Secretary s1 = new Secretary("Undergraduate secretary", degreeLevel);
		Secretary s2 = new Secretary("Undergraduate secretary", degreeLevel);
		Secretary s3 = new Secretary("Undergraduate secretary", degreeLevel);
		
		Secretary savedSecretary1 = dbTesting.inTransaction(() -> dao.persist(s1));
		Secretary savedSecretary2 = dbTesting.inTransaction(() -> dao.persist(s2));
		Secretary savedSecretary3 = dbTesting.inTransaction(() -> dao.persist(s3));

		assertEquals(3, dao.findAll().size());

		Secretary deletedSubs3 = dao.delete(s3);

		assertEquals(2, dao.findAll().size());
		assertThat(dao.findAll(), not(hasItem(deletedSubs3)));

	}

}
