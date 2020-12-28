package com.java8.examples;

import com.java8.data.Student;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;


/*
    Question 1 : Traditional approach
    Question 2 : Behaviour parametrization
    Question 3 : Tackling verbosity
    Question 4 : Lambda expression
 */
public class StudentManagement {

    public static void main(String[] a){

        List<Student> students = Arrays.asList(
                new Student(1L,"Ansu","Fati",LocalDateTime.of(1991,12,27,22,59),165,80),
                new Student(2L,"Anshu","Kumar",LocalDateTime.of(1994,1,2,14,55),169,80),
                new Student(3L,"Avinash","Kumar",LocalDateTime.of(1989,5,1,7,30),159,66),
                new Student(4L,"Sita","Ram",LocalDateTime.of(1991,12,20,1,34),175,59),
                new Student(5L,"Ahishesh","Tripathi",LocalDateTime.of(1991,12,27,10,40),165,89),
                new Student(6L,"Pushpendra","Patel",LocalDateTime.of(1991,12,27,11,40),155,69));

        //Question 1 : Filter students who are tall i.e. students have height greater than or equal to 160
        //Solution : Let's do iteration and have if condition to have this filter on height
        List<Student> tallStudents = filterTallStudents(students);
        for(Student student : tallStudents){
            System.out.println("Tall student :: "+student.toString());
        }

        //Filter students who are obese i.e. students have weight greater than or equal to 80
        //Solution : Let's do iteration and have if condition to have this filter on weight
        filterObeseStudents(students)
                .forEach(student -> System.out.println("Obese student :: " +student));

        //Question 2 : What's the problem here ?
        //DRY (don’t repeat yourself) principle of software engineering is violated
        //Idea : Essentially these methods are predicates because they take a list as argument and return boolean
        filterByStudentFilterPredicate(students,new StudentHeightFilterPredicate())
                .forEach(student -> System.out.println("StudentHeightFilterPredicate :: " +student));

        //OK....So it's just passing the behaviour; now the parameter StudentFilterPredicate encapsulate the behaviour
        //DRY principle of software engineering is no more a problem
        filterByStudentFilterPredicate(students,new StudentWeightFilterPredicate())
                .forEach(student -> System.out.println("StudentWeightFilterPredicate :: " +student));

        //OK. So I can even now filter based on any number of parameters and I have to pass the implementation
        //So Lets try to filter students on both the height and weight. Pretty Cool !!!
        filterByStudentFilterPredicate(students,new StudentHeightAndWeightFilterPredicate())
                .forEach(student -> System.out.println("StudentHeightAndWeightFilterPredicate :: " +student));

        //Question 3 : What's the problem again ? Verbosity
        //Why I need to write so many classes etc, etc to do this.
        //OK. I will use anonymous classes and then the behaviour can be inline
        filterByStudentFilterPredicate(students,new StudentFilterPredicate(){
            @Override
            public boolean test(Student student) {
                return student.getWeight() >= 80;
            }}).forEach(student -> System.out.println("Anonymous class student :: " +student));

        //OK. What now ? at least I do not have to write implementation classes
        //But wait... the verbosity is still there. It will look horrible boilerplate code
        //So Let's try to remove the boilerplate code using Lambda expression
        filter(students, (Student student) -> student.getWeight() >= 80)
                .forEach(student -> System.out.println("Lambda class student :: " +student));


    }

    public static List<Student> filter(List<Student> students, Predicate<Student> p){
        List<Student> result = new ArrayList<>();
        for(Student student : students){
            if(p.test(student)){
                result.add(student);
            }
        }
        return result;
    }


    private static List<Student> filterByStudentFilterPredicate(List<Student> students, StudentFilterPredicate studentFilterPredicate) {
        List<Student> filteredStudentByHeightPredicate = new ArrayList<>();
        for(Student student : students){
            if(studentFilterPredicate.test(student)){
                filteredStudentByHeightPredicate.add(student);
            }
        }
        return filteredStudentByHeightPredicate;
    }

    private static List<Student> filterTallStudents(List<Student> students) {
        List<Student> obeseStudents = new ArrayList<>();
        for(Student student : students){
            if(student.getHeight() >= 165){
                obeseStudents.add(student);
            }
        }
        return obeseStudents;
    }

    private static List<Student> filterObeseStudents(List<Student> students) {
        List<Student> obeseStudents = new ArrayList<>();
        for(Student student : students){
            if(student.getWeight() >= 80){
                obeseStudents.add(student);
            }
        }
        return obeseStudents;
    }

}
