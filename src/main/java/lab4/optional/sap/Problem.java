package lab4.optional.sap;

import lab4.optional.school.School;
import lab4.optional.student.Student;
import lombok.Data;

import java.util.*;

/**
 * This class's purpose is to store the data consisting of Students, Schools and their preferences.
 * It also solves the problem.
 */
public @Data
class Problem {

    private Set<Student> studentList;
    private Set<School> schoolList;
    private Map<Student, List<School>> studentsPreferences;
    private Map<School, List<Student>> schoolPreferences;

    /**
     * Constructor for problem class. It instructs the compiler what types of data structures to use
     * for the two lists and the two maps.
     */
    public Problem() {
        this.studentList = new HashSet<>(4);
        this.schoolList = new HashSet<>(3);
        this.studentsPreferences = new TreeMap<>();
        this.schoolPreferences = new TreeMap<>();
    }

    /**
     * Method to add students in the list using variadic parameters
     *
     * @param students variadic parameter of objects of type Student
     */
    public void addStudents(Student... students) {
        studentList.addAll(Arrays.asList(students));
        for (var student : students) {
            studentList.add(student);
            studentsPreferences.put(student, new ArrayList<>());
        }
    }

    /**
     * Method to add school in the list using variadic parameters
     *
     * @param schools variadic parameter of objects of type School
     */
    public void addSchools(School... schools) {
        schoolList.addAll(Arrays.asList(schools));
        for (var school : schools) {
            schoolList.add(school);
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
        if (!studentList.contains(student)) {
            System.err.println("The student doesnt exist in the students list of the problem.Add him/her first before adding his/her preferences.");
        } else
            studentsPreferences.get(student).addAll(Arrays.asList(schools));
    }

    /**
     * Method to add preferences to one school given as parameter already existing in the schools list.
     *
     * @param school   the school to add preferences too
     * @param students variadic parameter representing it's preferences
     */
    public void addStudentsPreferencesForSchool(School school, Student... students) {
        if (!schoolList.contains(school)) {
            System.err.println("The school doesnt exist in the schools list of the problem. Add it first before adding it's preferences.");
        } else
            schoolPreferences.get(school).addAll(Arrays.asList(students));
    }

    /**
     * This method solves the problem returning a Solution object;
     *
     * @return a Solution object containing a correct matching.
     */
    public Solution solveProblem() {
        Solution solution = new Solution();
        return solution;
    }

}
