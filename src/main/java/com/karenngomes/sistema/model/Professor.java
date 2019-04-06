package com.karenngomes.sistema.model;

import javax.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Professor extends Person {
	private Integer siap;

	public Professor(String firstName, String lastName, Integer siap) {
		super(firstName, lastName);
		this.siap = siap;
	}

}
