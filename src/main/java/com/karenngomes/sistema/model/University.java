package com.karenngomes.sistema.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	@Size(min=4, max=50)
	private String name;
	
	@OneToMany
	ArrayList <Department> departments = new ArrayList<Department>();
	
	public University(String name) {
		this.name = name;
	}
	
	public void setDepartment(Department department) {
		this.departments.add(department);
	}
	
	// add Equals and hash code
	
}
