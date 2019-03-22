package com.karenngomes.sistema.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

// import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Entity
@Getter
@EqualsAndHashCode(of = {"id"})
public class Professor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;
	private int siap;

	@OneToMany
	private Set<Subject> subjects = new HashSet<Subject>();

	public Professor(String name, int siap) {
		this.name = name;
		this.siap = siap;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSiap(int siap) {
		this.siap = siap;
	}

	public void setSubject(Subject subject) {
		this.subjects.add(subject);
	}

}
