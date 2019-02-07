package com.karenngomes.sistema.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	
	@Size.List ({
	    @Size(min=4, message="The field must be at least {min} characters"),
	    @Size(max=50, message="The field must be less than {max} characters")
	})
	private String name;
	
	@OneToMany
	ArrayList <Department> department = new ArrayList<Department>();
	
	public University(String name) {
		this.name = name;
	}
	
	// add Equals and hash code
	
}
