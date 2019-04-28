package com.karenngomes.sistema.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

import java.util.List;

@ToString
@Entity
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Student extends Person {
	@Setter
	private Integer credits = 0;

	public Student(String firstName, String lastName, Integer credits, List<Subject> completedSubjects) {
		this.credits = credits != null ? credits : 0;
	}

}
