package org.firesunset.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "оборудование_склада")
@Data
@JsonIgnoreProperties(value = {"employee"})
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "наименование")
    private String name;
    @ManyToOne
    @JoinColumn(name = "id_эксплуатирующего_сотрудника")
    @JsonBackReference
    private Employee employee;
}
