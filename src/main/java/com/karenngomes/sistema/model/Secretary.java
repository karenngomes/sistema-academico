package com.karenngomes.sistema.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.karenngomes.sistema.utils.AcademicTypes;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@EqualsAndHashCode(of = {"id"})
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Secretary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter
    private String name;
    private AcademicTypes type; // 1 - undergraduate, 2 - postgraduate
    
    @OneToMany(mappedBy="secretary")
    private List<Course> courses;
    
    public Secretary(String name, AcademicTypes type) {
        this.name = name;
        this.type = type;
        this.courses = new ArrayList<Course>();
    }
    

}