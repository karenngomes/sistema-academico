package com.karenngomes.sistema.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.karenngomes.sistema.utils.AcademicTypes;

// import lombok.EqualsAndHashCode;
import lombok.Getter;

@Entity
@Getter
//@EqualsAndHashCode
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String code;
	private int credits;
	private AcademicTypes type; //1 - undergraduate, 2 - postgraduate 
	private Course couse;
	private Professor professor;
	
	
	public Subject(String name) {
		this.name = name;
		// need add type here
	}
	
	// add Equals and hash code
	
}