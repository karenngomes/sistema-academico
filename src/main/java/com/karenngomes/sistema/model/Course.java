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
	
	
	public Course(String name, AcademicTypes type) {
		this.name = name;
		this.type = type;
	}
	
	public void setSubject(Subject subject) {
		AcademicTypes enumCourseType = this.getType();
		
		if(subject.getType() != enumCourseType) {
			System.out.println("Subject type isn't equal to course type");
		} else {
			this.subjects.add(subject);
		}
		
	}
	
	// add Equals and hash code
	
}
