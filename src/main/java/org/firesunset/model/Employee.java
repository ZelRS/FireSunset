package org.firesunset.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "cотрудники")
@Data
@JsonIgnoreProperties(value = {"equipments"})
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "имя")
    private String firstName;
    @Column(name = "фамилия")
    private String lastName;
    @Column(name = "возраст")
    private int age;
    @OneToMany(mappedBy = "employee")
    @JsonManagedReference
    private Collection<Equipment> equipments;
}
