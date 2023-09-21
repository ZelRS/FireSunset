package org.firesunset.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "Кабель_XLR_сигнал")
@Data
public class CableXLR {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "Длина")
    private Double length;
}
