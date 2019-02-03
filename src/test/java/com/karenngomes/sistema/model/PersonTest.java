package com.karenngomes.sistema.model;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
	
	public void testConstructor() {
		
		assertThrows(NullPointerException.class, () -> new University(null));
		assertThrows(IllegalArgumentException.class, () -> new University(""));
		
		University u1 = new University("k");
		
		// todo: vai ser aceito com uma letra?
		
		University u2 = new University("karen");
		
		assertEquals("karen", u2.getName());
	}

}
