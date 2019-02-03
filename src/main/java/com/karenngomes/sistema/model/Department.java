package com.karenngomes.sistema.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

// import lombok.EqualsAndHashCode;
import lombok.Getter;

@Entity
@Getter
//@EqualsAndHashCode
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@OneToOne
	Secretary graduate, posGraduate;
	
	public Department(String name, Secretary graduate, Secretary posGraduate) {
		this.name = name;
		this.graduate = graduate;
		this.posGraduate = posGraduate;
	}
	
	// add Equals and hash code
	
}