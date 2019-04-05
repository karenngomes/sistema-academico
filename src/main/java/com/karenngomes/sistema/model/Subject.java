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

	@NotNull
	private String name;

	@NotNull
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

	// @ElementCollection
	// List<String> requiredSubjects;

	/*
	 * @ElementCollection List<Long> completedStudents;
	 */

	@ManyToMany
	/*
	 * @JoinTable( name = "completed_students", joinColumns = @JoinColumn(name =
	 * "subject_id"), inverseJoinColumns = @JoinColumn(name = "enrollment_id"))
	 */
	private List<Enrollment> completedStudents = new ArrayList<Enrollment>();

	@ManyToMany
	private List<Enrollment> enrollments = new ArrayList<Enrollment>();

	/*
	 * public Subject(String name, String code, AcademicTypes type, Integer credits,
	 * Integer requiredCredits, List<String> requiredDisciplines) { this.name =
	 * name; this.code = code; this.type = type; this.credits = credits != null ?
	 * credits : 0; this.requiredCredits = requiredCredits != null ? requiredCredits
	 * : 0; this.requiredSubjects = requiredSubjects != null ? requiredSubjects :
	 * new ArrayList<>(); this.students = new ArrayList<>(); }
	 */
	public boolean verifyStudentHasRequiredSubject(Enrollment enrollment) {
		return enrollment.getCompletedSubjects().containsAll(requiredSubjects);
	}

	/*
	 * public boolean addCompletedEnrollment(Enrollment enrollment) {
	 * if(!this.completedStudents.contains(enrollment.getId())) return
	 * this.students.add(enrollment.getId()); return false; }
	 * 
	 * 
	 * public boolean removeStudent(Enrollment enrollment) { boolean isDeleted =
	 * this.students.remove(enrollment.getId());
	 * 
	 * if(isDeleted) this.completedStudents.add(enrollment.getId());
	 * 
	 * return isDeleted; }
	 */

}