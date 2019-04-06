package com.karenngomes.sistema.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Student extends Person {
    @Setter
    private Integer credits = 0;
    
    // @ElementCollection
    // private List<String> completedSubjects = new ArrayList<String>();
    
    public Student(String firstName, String lastName, Integer credits, List<Subject> completedSubjects) {
    	this.credits = credits != null ? credits : 0;
    }

}

