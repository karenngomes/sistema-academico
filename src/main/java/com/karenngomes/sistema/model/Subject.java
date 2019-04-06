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

	@NotNull(message = "Name can't be null")
	private String name;

	@NotNull(message = "Code can't be null")
	@Column(unique = true)
	private String code;

	private Integer credits = 0, requiredCredits = 0;

	@NotNull(message = "Type can't be null")
	private AcademicTypes type; // 1 - undergraduate, 2 - postgraduate

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Course course;
	
	@ManyToOne
	private Professor professor;

	@ManyToMany
	@JoinTable(name = "required_subjects", joinColumns = @JoinColumn(name = "subject_id"), inverseJoinColumns = @JoinColumn(name = "required_subjects_id"))
	private List<Subject> requiredSubjects = new ArrayList<Subject>();


	//private List<Enrollment> completedStudents = new ArrayList<Enrollment>();

	//@ManyToMany
	// private List<Enrollment> enrollments = new ArrayList<Enrollment>();

	/*
	public boolean verifyStudentHasRequiredCredits(Enrollment enrollment) {
		return enrollment.getStudent().getCredits() >= this.requiredCredits;
	}
	
	public boolean verifyStudentHasRequiredSubject(Enrollment enrollment) {
		return enrollment.getCompletedSubjects().containsAll(requiredSubjects);
	}*/
	
	public Subject(String name, String code, AcademicTypes type) {
		this.name = name;
		this.code = code;
		this.type = type;
	}

}