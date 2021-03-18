package lab4.bonus;


import lab4.bonus.sap.Problem;
import lab4.bonus.sap.Solution;
import lab4.bonus.school.School;
import lab4.bonus.student.Student;

import java.util.stream.IntStream;

public class MainBonus {
    public static void main(String[] args) {


        Problem problem = new Problem();


        var students = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Student("S" + i))
                .toArray(Student[]::new);

        int[] capacity = {1, 1, 2};
        var schools = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new School("H" + i, capacity[i]))
                .toArray(School[]::new);


        //create the instance of the problem
        problem.addStudents(students);
        problem.addSchools(schools);

        //specify the preferences for each student
        problem.addSchoolsPreferencesForStudent(students[0], schools);                                              //  S0: (H0, H1, H2)
        problem.addSchoolsPreferencesForStudent(students[1], schools);                                              //  S1: (H0, H1, H2)
        problem.addSchoolsPreferencesForStudent(students[2], schools[0], schools[1]);                               //  S2: (H0, H1)
        problem.addSchoolsPreferencesForStudent(students[3], schools[0], schools[2]);                               //  S3: (H0, H2)

        //set preferences for each school
        problem.addSchoolsPreferencesForStudent(schools[0], students[0], students[1], students[2]);                  // H0: (S3, S0, S1, S2)
        problem.addSchoolsPreferencesForStudent(schools[1], students[2], students[1]);                               // H1: (S0, S2, S1)
        problem.addSchoolsPreferencesForStudent(schools[2], students[1], students[3]);                               // H2: (S0, S1, S3)

        Solution solution = problem.solveGaleShapleyAlgorithm();
        solution.printSolution();
    }
}
