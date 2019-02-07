package com.karenngomes.sistema.model;

import java.util.ArrayList;

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
	private ArrayList <Subject> subjects = new ArrayList<Subject>();
	
	
	public Course(String name) {
		this.name = name;
		// need add type here
	}
	
	// add Equals and hash code
	
}
