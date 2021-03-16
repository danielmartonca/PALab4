package lab4.optional;


import com.github.javafaker.Faker;
import lab4.optional.sap.Problem;
import lab4.optional.sap.Solution;
import lab4.optional.school.School;
import lab4.optional.student.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.stream.IntStream;

public class MainOptional {
    public static void main(String[] args) {


        Problem problem = new Problem();

        //generate fake random names
        Faker faker = new Faker();

        var students = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Student(faker.name().fullName()))
                .toArray(Student[]::new);

        int[] capacity = {1, 1, 2};
        var schools = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new School(faker.university().name(), capacity[i]))
                .toArray(School[]::new);

        //set random grade for all students to all the schools
        for (Student student : students)
            for (School school : schools)
                student.setGradeForSchool(school, 5 + Math.random() * 5);

        //create the instance of the problem
        problem.addStudents(students);
        problem.addSchools(schools);

        //specify the preferences for each student
        problem.addSchoolsPreferencesForStudent(students[0], schools);                                              //  S0: (H0, H1, H2)
        problem.addSchoolsPreferencesForStudent(students[1], schools);                                              //  S1: (H0, H1, H2)
        problem.addSchoolsPreferencesForStudent(students[2], schools[0], schools[1]);                               //  S2: (H0, H1)
        problem.addSchoolsPreferencesForStudent(students[3], schools[0], schools[2]);                               //  S3: (H0, H2)

        //set preferences of schools based on the grades each student had at the each school's exam
        problem.setStudentPreferencesForSchools();

        //check if a given list of preferences is valid for student using streams
        //create the list
        List<School> studentPreferences = new ArrayList<>(Arrays.asList(schools));
        System.out.println("The list of preferences is: " + studentPreferences + "\n");

        //create a stream that calls the lambda expression for each entry set of the map studentPreferences
        problem.getStudentsPreferences().entrySet().stream()
                .forEach((entry) -> {
                    if (entry.getValue().equals(studentPreferences))
                        System.out.println("The student \"" + entry.getKey().getName() + "\" accepts the list of preferences.");
                });

        //choose the student to check if it's on top of the list
        Student topStudent = students[0];
        System.out.println("\n\nThe list of schools who have student \"" + topStudent.getName() + "\" as their top target is:");

        //create a stream that check for every school if topStudent is the student on top of the list of preferences
        problem.getSchoolPreferences().entrySet().stream()
                .forEach((entry) -> {
                    if (entry.getValue().get(0).equals(topStudent))
                        System.out.println("The school \"" + entry.getKey().getName() + "\".");
                });

        Solution solution = problem.solveProblem();
        solution.printSolution();
    }
}
