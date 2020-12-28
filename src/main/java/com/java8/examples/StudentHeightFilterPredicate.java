package com.java8.examples;

import com.java8.data.Student;

public class StudentHeightFilterPredicate implements StudentFilterPredicate{

    @Override
    public boolean test(Student student) {
        return student.getHeight() > 160;
    }
}
