package com.java8.examples;

import com.java8.data.Student;

public interface StudentFilterPredicate {

    //Using strategy design pattern to solve the problem of DRY (https://en.wikipedia.org/wiki/Strategy_pattern)
    //By creating a predicate interface and having different implementations
    boolean test(Student student);
}
