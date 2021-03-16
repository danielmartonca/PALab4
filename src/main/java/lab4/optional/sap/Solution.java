package lab4.optional.sap;

import lab4.optional.school.School;
import lab4.optional.student.Student;
import lombok.Data;

import java.util.Map;
import java.util.TreeMap;


public @Data
class Solution {

    private Map<Student, School> matching;

    public Solution() {
        this.matching = new TreeMap<>();
    }

    void addSolution(Student student, School school) {
        matching.put(student, school);
    }

    void printSolution() {
        System.out.println("\nFound the fallowing matching for the problem:\n[ ");
        for (var student : matching.entrySet())
            System.out.print('(' + student.getKey().getName() + ':' + student.getValue().getName() + ") ");
        System.out.print("].");
    }
}
