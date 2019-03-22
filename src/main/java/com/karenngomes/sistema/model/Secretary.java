package com.karenngomes.sistema.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.karenngomes.sistema.utils.AcademicTypes;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Entity
@Getter
@EqualsAndHashCode(of = {"id"})
public class Secretary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private AcademicTypes type; // 1 - undergraduate, 2 - postgraduate

    @OneToMany
    private Set<Course> courses = new HashSet<Course>(); // it needs be checked if types are equals

    public Secretary(String name, AcademicTypes type) {
        this.name = name;
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(Course course) {
        AcademicTypes enumSecretaryType = this.getType();

        if (course.getType() != enumSecretaryType) {
            System.out.println("Course type isn't equal to secretary type");
        } else {
            this.courses.add(course);
        }

    }

}