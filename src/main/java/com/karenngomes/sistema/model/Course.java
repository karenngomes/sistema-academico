package com.karenngomes.sistema.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.karenngomes.sistema.utils.AcademicTypes;

import lombok.AccessLevel;
// import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@EqualsAndHashCode(of = {"id"})
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter
    private String name;
    private AcademicTypes type; //1 - undergraduate, 2 - postgraduate
    
    @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @NotNull(message = "Secretary can't be null")
    private Secretary secretary;
    
    @OneToMany(mappedBy="course")
    private List<Subject> subjects;
    
    public Course(String name, AcademicTypes type, Secretary secretary) {
        this.name = name;
        this.type = type;
        this.secretary = secretary;
        this.subjects = new ArrayList<Subject>();
    }

}
