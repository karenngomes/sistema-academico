package com.karenngomes.sistema.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Enrollment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Column(unique = true)
	private int number;

	@ManyToOne
	private Course course;

	@Setter
	private boolean status = true;

	@OneToOne
	@NotNull
	private Student student;

	@ManyToMany(mappedBy = "completedStudents")
	// @JoinTable(name = "completed_subjects", joinColumns = @JoinColumn(name =
	// "enrollment_id"), inverseJoinColumns = @JoinColumn(name = "subject_id"))
	private List<Subject> completedSubjects = new ArrayList<Subject>();

	@ManyToMany(mappedBy = "enrollments")
	private List<Subject> currentSubjects;

	public Enrollment(Student student, int number) {
		this.student = student;
		this.number = number;
	}
	/*

	public String addSubject(Subject subject) {
		if(!subject.verifyStudentHasRequiredCredits(this)) {
			return "STUDENT DOESN'T HAVE CREDITS ENOUGH";
		}
		if(this.completedSubjects.contains(subject)) {
			return "COMPLETED_SUBJECT";
		}
		if (this.currentSubjects.contains(subject)) {
			return "CURRENT_SUBJECT";
		}
		this.currentSubjects.add(subject);
		return "SUCESS";
	}
*/
	public boolean completeSubject(Subject subject) {
		if (this.currentSubjects.contains(subject)) {
			int credits = this.student.getCredits() + subject.getCredits();
			this.student.setCredits(credits);
			this.currentSubjects.remove(subject);
			return this.completedSubjects.add(subject);
		}

		return false;
	}

}
