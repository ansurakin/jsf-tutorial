package com.codenotfound.primefaces;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Entity(name = "Car")
@Data
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;

    private int year;

    private String color;

    public Car() {
    }

    public Car(Long id, String brand, int year, String color) {
        this.id = id;
        this.brand = brand;
        this.year = year;
        this.color = color;
    }

}
