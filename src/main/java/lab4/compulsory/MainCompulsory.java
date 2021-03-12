package lab4.compulsory;

import lab4.compulsory.school.School;
import lab4.compulsory.student.Student;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.IntStream;

public class MainCompulsory {
    public static void main(String[] args) {

        var students = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Student("S" + i))
                .toArray(Student[]::new);


        var schools = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new School("H" + i, 0))
                .toArray(School[]::new);

        schools[0].setCapacity(1);
        schools[1].setCapacity(2);
        schools[2].setCapacity(2);


        /*
        H0: (S3, S0, S1, S2)
        H1: (S0, S2, S1)
        H2: (S0, S1, S3)
         */

        /*
        S0: (H0, H1, H2)
        S1: (H0, H1, H2)
        S2: (H0, H1)
        S3: (H0, H2)
         */


        List<Student> studentList = new LinkedList<>(Arrays.asList(students));
        studentList.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.compareTo(o2);
            }
        });

        Set<School> set = new TreeSet<>(Arrays.asList(schools));


        Map<Student, List<School>> studentsMap = new TreeMap<>();
        studentsMap.put(students[0], new ArrayList<School>(Arrays.asList(schools[0], schools[1], schools[2])));
        studentsMap.put(students[1], new ArrayList<School>(Arrays.asList(schools[0], schools[1], schools[2])));
        studentsMap.put(students[2], new ArrayList<School>(Arrays.asList(schools[0], schools[1])));
        studentsMap.put(students[3], new ArrayList<School>(Arrays.asList(schools[0], schools[1])));

        System.out.println("\nStudents map: ");
        for (Map.Entry<Student, List<School>> entry : studentsMap.entrySet())
            System.out.println(entry);


        Map<School, List<Student>> schoolsMap = new HashMap<>();
        schoolsMap.put(schools[0], new ArrayList<Student>(Arrays.asList(students[3], students[0], students[1], students[2])));
        schoolsMap.put(schools[1], new ArrayList<Student>(Arrays.asList(students[0], students[2], students[1])));
        schoolsMap.put(schools[2], new ArrayList<Student>(Arrays.asList(students[0], students[1], students[3])));


        System.out.println("\n\nSchools map: ");
        for (Map.Entry<School, List<Student>> entry : schoolsMap.entrySet())
            System.out.println(entry);
    }
}
