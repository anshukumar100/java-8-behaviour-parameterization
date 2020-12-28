package com.java8.examples;

import com.java8.data.Student;

public class StudentHeightAndWeightFilterPredicate implements StudentFilterPredicate{

    @Override
    public boolean test(Student student) {

        return student.getWeight() >= 80 && student.getHeight() > 160;
    }
}
