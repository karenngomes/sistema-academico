package com.karenngomes.sistema.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Test {
	@Getter
	@Setter
	private String name = "Willy";
	private int age = 30;
	
}
