package com.karenngomes.sistema.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.karenngomes.sistema.utils.AcademicTypes;

// import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@EqualsAndHashCode(of = {"id"})
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter
    private String name;
    private AcademicTypes type; //1 - undergraduate, 2 - postgraduate
    @OneToMany
    private Set<Subject> subjects = new HashSet<Subject>();

    public Course(String name, AcademicTypes type) {
        this.name = name;
        this.type = type;
    }

    public void setSubject(Subject subject) {
        AcademicTypes enumCourseType = this.getType();

        if (subject.getType() != enumCourseType) {
            System.out.println("Subject type isn't equal to course type");
        } else {
            this.subjects.add(subject);
        }

    }

}
