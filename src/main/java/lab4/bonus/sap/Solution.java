package lab4.bonus.sap;

import lab4.bonus.school.School;
import lab4.bonus.student.Student;
import lombok.Data;

import java.util.Map;
import java.util.TreeMap;

/**
 * This class's purpose is to store the solution of the problem consisting of pairs (Student:School)
 * It has methods to store and print the matches.
 */
public @Data
class Solution {

    private Map<Student, School> matching;

    /**
     * Constructor for Solution
     */
    public Solution() {
        this.matching = new TreeMap<>();
    }

    /**
     * Add a match based on the parameters student and school
     *
     * @param student the student of the match
     * @param school  the school of the match
     */
    void addSolution(Student student, School school) {
        matching.put(student, school);
    }

    /**
     * This method prints the solution on the screen under the next format:
     * [ (student:school),(student:school),.....(student:school) ]
     */
    public void printSolution() {
        System.out.print("\nFound the fallowing matching for the problem:\n[ ");
        for (var student : matching.entrySet())
            System.out.print('(' + student.getKey().getName() + ':' + student.getValue().getName() + "), ");
        System.out.print("].");
    }
}
