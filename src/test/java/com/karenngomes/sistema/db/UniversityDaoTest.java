package com.karenngomes.sistema.db;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.karenngomes.sistema.model.University;

import io.dropwizard.testing.junit5.DAOTestExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import lombok.SneakyThrows;

@ExtendWith(DropwizardExtensionsSupport.class)
public class UniversityDaoTest {
	
	public DAOTestExtension dbTesting = DAOTestExtension.newBuilder()
            .addEntityClass(University.class)
            //.addEntityClass(University.class)
            .build();
    
    private UniversityDao dao;

    @BeforeEach
    @SneakyThrows
    public void setUp() {
        System.out.println("setUp");
        dao = new UniversityDao(dbTesting.getSessionFactory());
    }
    
    @Test
    public void testCRUD() {

        System.out.println("testCRUD");
        
        University u1 = new University("c1");
        
        University saved = dbTesting.inTransaction(() -> dao.persist(u1));
        
        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(u1.getName(), saved.getName());

//        assertNotNull(null);
    }
}
