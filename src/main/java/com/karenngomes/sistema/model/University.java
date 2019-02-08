package com.karenngomes.sistema.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Length.List;

// import org.hibernate.validator.constraints.Length;

// import lombok.EqualsAndHashCode;
import lombok.Getter;

@Entity
@Getter
//@EqualsAndHashCode
public class University {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull

	@List({ @Length(min = 4, message = "The field must be at least 4 characters"),
			@Length(max = 20, message = "The field must be less than 20 characters") })
	private String name;

	@OneToMany
	private Set<Department> departments = new HashSet<Department>();

	public University(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDepartment(Department department) {
		this.departments.add(department);
	}

	// add Equals and hash code

}
