package com.karenngomes.sistema.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// import lombok.EqualsAndHashCode;
import lombok.Getter;

@Entity
@Getter
//@EqualsAndHashCode
public class Professor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private ArrayList <Subject> subjects = new ArrayList<Subject>();

	
	public Professor(String name) {
		this.name = name;
	}
	
	public void setSubject(Subject subject) {
		this.subjects.add(subject);
	}
	
	// add Equals and hash code
	
}
