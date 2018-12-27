package com.karenngomes.sistema;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.karenngomes.sistema.StringBuilder;

public class TestStringBuilder {
	
	@Test
	void testJuntaTexto() {
		 StringBuilder str = new StringBuilder();
		 String result = str.juntaText("texto", "junto");
		 
		 assertEquals("textojunto", result, "nao foi");
	}
	
}
