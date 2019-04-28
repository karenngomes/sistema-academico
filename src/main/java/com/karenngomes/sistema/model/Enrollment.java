package com.karenngomes.sistema.model;

import java.util.List;

import javax.persistence.*;
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
	private Long number;

	@ManyToOne
	@Setter
	@NotNull(message = "Course can't be null")
	private Course course;

	@Setter
	private boolean status = true;

	@OneToOne
	@Setter
	@NotNull
	private Student student;

	@ManyToMany
	@JoinTable(name="current_subjects", joinColumns=
			{@JoinColumn(name="enrollment_id")}, inverseJoinColumns=
			{@JoinColumn(name="subject_id")})
	private List<Subject> currentSubjects;

	@ManyToMany
	@JoinTable(name="completed_subjects", joinColumns=
			{@JoinColumn(name="enrollment_id")}, inverseJoinColumns=
			{@JoinColumn(name="subject_id")})
	private List<Subject> completedSubjects;

	public Enrollment(Student student) {
		this.student = student;
	}
	
	public Enrollment(Long number, Course course, Student student) {
		// TODO Auto-generated constructor stub
		this.number = number;
		this.course = course;
		this.student = student;
	}

	public String addSubject(Subject subject) {
		if(this.completedSubjects.contains(subject)) {
			return "COMPLETED_SUBJECT";
		}

		if (this.currentSubjects.contains(subject)) {
			return "CURRENT_SUBJECT";
		}
		this.currentSubjects.add(subject);

		return "SUCCESS";
	}

	public boolean completeSubject(Subject subject) {
		if(this.currentSubjects.contains(subject)) {
			int credits = this.student.getCredits() + subject.getCredits();
			this.student.setCredits(credits);
			this.currentSubjects.remove(subject);
			return this.completedSubjects.add(subject);
		}

		return false;
	}


}
