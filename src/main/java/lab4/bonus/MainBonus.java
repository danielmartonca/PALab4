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

        int[] capacity = {1, 2, 2};
        var schools = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new School("H" + i, capacity[i]))
                .toArray(School[]::new);


        //create the instance of the problem
        problem.addStudents(students);
        problem.addSchools(schools);

        //specify the preferences for each student
        //student 0
        problem.addPreferenceForStudent(students[0], schools[0]);                                               //  S0: (H0,
        problem.addPreferenceForStudent(students[0], schools[1]);                                               //  S0:  H1,
        problem.addPreferenceForStudent(students[0], schools[2]);                                               //  S0:  H2 )
        //student 1
        problem.addPreferenceForStudent(students[1], schools[0]);                                               //  S1: (H0,
        problem.addPreferenceForStudent(students[1], schools[1], schools[2]);                                   //  S1:  H1=H2 )
        //student 2
        problem.addPreferenceForStudent(students[2], schools[0], schools[1]);                                   //  S2: (H0=H1,
        problem.addPreferenceForStudent(students[2], schools[2]);                                               //  S2:  H2 )

        //student 3
        problem.addPreferenceForStudent(students[3], schools[2], schools[1]);                                   //  S3:  (H2=H1 )


        //specify the preferences for each school
        //school 0
        problem.addPreferenceForSchool(schools[0], students[3], students[0]);                                     //   H0: (S3=S0,
        problem.addPreferenceForSchool(schools[0], students[1], students[2]);                                     //   H0:  S1=S2)
        //school 1
        problem.addPreferenceForSchool(schools[1], students[0]);                                                  //   H1: (S0,
        problem.addPreferenceForSchool(schools[1], students[2], students[1]);                                     //   H1:  S2=S1)
        //school 2
        problem.addPreferenceForSchool(schools[2], students[0]);                                                  //   H2: (S0,
        problem.addPreferenceForSchool(schools[2], students[1]);                                                  //   H2:  S1,
        problem.addPreferenceForSchool(schools[2], students[3]);                                                  //   H2:  S3)


        Solution solution = problem.solveGaleShapleyAlgorithm();
        solution.printSolution();
    }
}
