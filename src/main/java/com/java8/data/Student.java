package com.java8.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Student {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDateTime dob;
    private int height;         //in cm
    private double weight;      //in kg
}
