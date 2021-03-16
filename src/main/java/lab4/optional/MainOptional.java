package lab4.optional;


import com.github.javafaker.Faker;
import lab4.optional.sap.Problem;
import lab4.optional.school.School;
import lab4.optional.student.Student;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class MainOptional {
    public static void main(String[] args) {


        Problem problem = new Problem();

        //generate fake random names
        Faker faker = new Faker();

        var students = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Student(faker.name().fullName(), Math.random() * 100))
                .toArray(Student[]::new);

        int[] capacity = {1, 1, 2};
        var schools = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new School(faker.university().name(), capacity[i]))
                .toArray(School[]::new);

        //create the instance of the problem
        problem.addStudents(students);
        problem.addSchools(schools);

        problem.addSchoolsPreferencesForStudent(students[0], schools);                                              //  S0: (H0, H1, H2)
        problem.addSchoolsPreferencesForStudent(students[1], schools);                                              //  S1: (H0, H1, H2)
        problem.addSchoolsPreferencesForStudent(students[2], schools[0], schools[1]);                               //  S2: (H0, H1)
        problem.addSchoolsPreferencesForStudent(students[3], schools[0], schools[2]);                               //  S3: (H0, H2)

        problem.addStudentsPreferencesForSchool(schools[0], students[3], students[0], students[1], students[2]);    //H0: (S3, S0, S1, S2)
        problem.addStudentsPreferencesForSchool(schools[1], students[0], students[2], students[1]);                 //H1: (S0, S2, S1)
        problem.addStudentsPreferencesForSchool(schools[2], students[0], students[1], students[3]);                 //H2: (S0, S1, S3)


        //check if a given list of preferences is valid for student using streams
        //create the list
        List<School> studentPreferences = new ArrayList<>(Arrays.asList(schools));


        Consumer<Map.Entry<Student,List<School>>> consumer= (student) ->
        {

        };
        problem.getStudentsPreferences().entrySet()
                .forEach(consumer);
    }
}
