package com.karenngomes.sistema.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.karenngomes.sistema.utils.AcademicTypes;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Entity
@Getter
@EqualsAndHashCode(of = { "id" })
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Secretary {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Setter
	private String name;
	
	@Setter
	@NotNull(message = "Type can't be null")
	private AcademicTypes type;

	public Secretary(String name, AcademicTypes type) {
		this.name = name;
		this.type = type;
	}

	public Secretary(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}

}