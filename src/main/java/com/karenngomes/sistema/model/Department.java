package com.karenngomes.sistema.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.karenngomes.sistema.utils.AcademicTypes;

import lombok.AccessLevel;
// import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Entity
@Getter
@Setter
@EqualsAndHashCode(of = { "id" })
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Size(min = 2, message = "The field must be at least 2 characters")
	private String name;

	@OneToOne
	Secretary underGraduate;
	@OneToOne
	Secretary postGraduate;

	@ManyToOne
	@NotNull(message = "Must be add a University")
	private University university;

	public Department(String name) {
		this.name = name;
	}

	public Department(String name, University university) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.university = university;
	}
}