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
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private AcademicTypes type; //1 - undergraduate, 2 - postgraduate 
	

	
	public Course(String name) {
		this.name = name;
		// need add type here
	}
	
	// add Equals and hash code
	
}
