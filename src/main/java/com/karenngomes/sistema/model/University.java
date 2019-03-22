package com.karenngomes.sistema.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Length.List;

@Entity
@Getter
@EqualsAndHashCode(of = {"id"})
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull

    @List({@Length(min = 4, message = "The field must be at least 4 characters"),
            @Length(max = 20, message = "The field must be less than 20 characters")})
    @Setter
    private String name;

    @OneToMany(cascade = {CascadeType.ALL})
    private Set<Department> departments = new HashSet<Department>();

    public University(String name) {
        this.name = name;
    }

    public void setDepartment(Department department) {
        this.departments.add(department);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((departments == null) ? 0 : departments.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof University) {
            University other = (University) obj;
            return (other.getName().equals(this.getName()) && other.getDepartments().equals(this.getDepartments()));
        }
        return false;

    }

}
