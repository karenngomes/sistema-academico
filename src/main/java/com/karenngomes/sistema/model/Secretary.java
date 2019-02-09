package com.karenngomes.sistema.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.karenngomes.sistema.utils.AcademicTypes;

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
	private AcademicTypes type; // 1 - undergraduate, 2 - postgraduate

	@OneToMany
	private Set<Course> courses = new HashSet<Course>(); // it needs be checked if types are equals

	public Secretary(String name, AcademicTypes type) {
		this.name = name;
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCourse(Course course) {
		AcademicTypes enumSecretaryType = this.getType();

		if (course.getType() != enumSecretaryType) {
			System.out.println("Course type isn't equal to secretary type");
		} else {
			this.courses.add(course);
		}

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courses == null) ? 0 : courses.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Secretary) {
			Secretary other = (Secretary) obj;
			return (other.getType().equals(this.getType()) && other.getCourses().equals(this.getCourses()));
		}
		return false;

	}

}