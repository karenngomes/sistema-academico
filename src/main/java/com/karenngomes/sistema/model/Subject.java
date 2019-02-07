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
	private int credits = 0;
	private AcademicTypes type; // 1 - undergraduate, 2 - postgraduate
	private Course couse;
	private Professor professor;

	public Subject(String name, String code, int credits, AcademicTypes type, Course couse, Professor professor) {
		super();
		this.name = name;
		this.code = code;
		this.credits = credits;
		this.type = type;
		this.couse = couse;
		this.professor = professor;
	}

	// add Equals and hash code

}