package com.karenngomes.sistema.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// import lombok.EqualsAndHashCode;
import lombok.Getter;

@Entity
@Getter
//@EqualsAndHashCode
public class Secretary {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;

	
	public Secretary(String name) {
		this.name = name;
	}
	
	// add Equals and hash code
	
}