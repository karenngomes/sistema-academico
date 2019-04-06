package com.karenngomes.sistema.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Student extends Person {
    @Setter
    private Integer credits = 0;

    
    public Student(String firstName, String lastName) {
    	this.credits = credits != null ? credits : 0;
    }

}

