package com.karenngomes.sistema.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.karenngomes.sistema.utils.AcademicTypes;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = { "id" })
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private String name;

	@NotNull
	//@Column(unique = true)
	private String code;

	private Integer credits = 0, requiredCredits = 0;

	@NotNull(message = "Type can't be null")
	private AcademicTypes type; 

	@JsonProperty(access = Access.AUTO)
	@ManyToOne
	private Course course;

	@ManyToOne
	private Professor professor;

	@ManyToMany
	@JoinTable(name = "required_subjects", joinColumns = @JoinColumn(name = "subject_id"), inverseJoinColumns = @JoinColumn(name = "required_subjects_id"))
	private List<Subject> requiredSubjects = new ArrayList<Subject>();

	public boolean verifyStudentHasRequiredSubject(Enrollment enrollment) {
		return enrollment.getCompletedSubjects().containsAll(requiredSubjects);
	}

	public boolean addRequiredSubject(Subject subject) {

		if (!this.requiredSubjects.contains(subject)) {
			return this.requiredSubjects.add(subject);
		}

		return false;
	}

	public Subject(String name, String code, AcademicTypes type, Course course) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.code = code;
		this.type = type;
		this.course = course;
	}
	/*
	public Subject(AcademicTypes type, Integer credits, Integer requiredCredits) {
		// TODO Auto-generated constructor stub
		this.type = type;
		this.credits = credits;
		this.requiredCredits = requiredCredits;
	}*/

}