package lab4.bonus.sap;

import lab4.bonus.school.School;
import lab4.bonus.student.Student;
import lombok.Data;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

/**
 * This class's purpose is to store the data consisting of Students, Schools and their preferences.
 * It also solves the problem.
 */
public @Data
class Problem {

    private Set<Student> studentsSet;
    private Set<School> schoolsSet;
    private Map<Student, List<School>> studentsPreferences;
    private Map<School, List<Student>> schoolPreferences;

    /**
     * Constructor for problem class. It instructs the compiler what types of data structures to use
     * for the two lists and the two maps.
     */
    public Problem() {
        this.studentsSet = new HashSet<>(4);
        this.schoolsSet = new HashSet<>(3);
        this.studentsPreferences = new HashMap<>();
        this.schoolPreferences = new HashMap<>();
    }

    /**
     * Method to add students in the list using variadic parameters
     *
     * @param students variadic parameter of objects of type Student
     */
    public void addStudents(Student... students) {
        for (var student : students)
            if (!studentsSet.contains(student)) {
                studentsSet.add(student);
                studentsPreferences.put(student, new ArrayList<>());
            }
    }

    /**
     * Method to add school in the list using variadic parameters
     *
     * @param schools variadic parameter of objects of type School
     */
    public void addSchools(School... schools) {

        for (var school : schools)
            if (!schoolsSet.contains(school)) {
                schoolsSet.add(school);
                schoolPreferences.put(school, new ArrayList<>());
            }
    }

    /**
     * Method to add preferences to one student given as parameter already existing in the students list.
     *
     * @param student the student to add preferences too
     * @param schools variadic parameter representing it's preferences
     */
    public void addSchoolsPreferencesForStudent(Student student, School... schools) {
        if (!studentsSet.contains(student)) {
            System.err.println("The student doesnt exist in the students list of the problem.Add him/her first before adding his/her preferences.");
        } else
            studentsPreferences.get(student).addAll(Arrays.asList(schools));
    }

    /**
     * Method to add preferences to a school given as parameter already existing in the schools list.
     *
     * @param school   the student to add preferences too
     * @param students variadic parameter representing it's preferences
     */
    public void addSchoolsPreferencesForStudent(School school, Student... students) {

        if (!schoolsSet.contains(school)) {
            System.err.println("The school doesnt exist in the schools list of the problem.Add it first before adding it's preferences.");
        } else
            schoolPreferences.get(school).addAll(Arrays.asList(students));
    }



    /**
     * This method solves the problem returning a Solution object;
     *
     * @return a Solution object containing a correct matching.
     */
    public Solution solveGaleShapleyAlgorithm() {
        Solution solution = new Solution();     //create a new solution object where we store the matches

        return solution;
    }

}
