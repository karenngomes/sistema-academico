package com.karenngomes.sistema.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.karenngomes.sistema.utils.AcademicTypes;

// import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Entity
@Getter
@EqualsAndHashCode(of = {"id"})
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 2, message = "The field must be at least 2 characters")
    private String name;

    @OneToOne
    Secretary underGraduate;
    @OneToOne
    Secretary postGraduate;

    public Department(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setSecretary(Secretary secretary) {
        AcademicTypes enumType = secretary.getType();
        // System.out.println(enumType);
        switch (enumType) {
            case UNDERGRADUATE:
                if (this.underGraduate == null)
                    this.underGraduate = secretary;
                else
                    System.out.println("secretary undergraduate already exists");
                break;
            case POSTGRADUATE:
                if (this.postGraduate == null)
                    this.postGraduate = secretary;
                else
                    System.out.println("secretary postgraduate already exists");
                break;
            default:
                System.out.println("Invalid type");
        }
    }

}