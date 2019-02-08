package com.karenngomes.sistema.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
	
	@OneToOne
	private Course course;
	@OneToOne
	private Professor professor;

	public Subject(String name, String code, AcademicTypes type, Course course) {
		this.name = name;
		this.code = code;
		this.type = type;
		this.course = course;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	/*
	public void setType(AcademicTypes type) {
		this.type = type;
	}*/

	public void setCourse(Course course) {
		this.course = course;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	// add Equals and hash code

}