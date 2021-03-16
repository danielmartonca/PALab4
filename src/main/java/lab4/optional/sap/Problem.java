package lab4.optional.sap;

import lab4.optional.school.School;
import lab4.optional.student.Student;
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
     * Method to add each school preferences based on what grade each student has received at the exam.
     * It finds the pairs of (student, grade), sorts them by grade then adds them in the preferences list based on the sort.
     */
    public void setStudentPreferencesForSchools() {

        for (School school : schoolsSet) {

            List<Pair<Student, Double>> grades = new ArrayList<>();
            for (Student student : studentsSet)
                grades.add(Pair.of(student, student.getGradeForSchool(school)));

            grades.sort(new Comparator<Pair<Student, Double>>() {
                @Override
                public int compare(Pair<Student, Double> o1, Pair<Student, Double> o2) {
                    return -o1.getRight().compareTo(o2.getRight());
                }
            });

            for (var grade : grades)
                schoolPreferences.get(school).add(grade.getLeft());
        }

    }

    /**
     * This method find the first student in the List parameter who doesn't have match ( exists in the Set parameter ).
     *
     * @param studentsNotMatched   the Set of students with no matching
     * @param schoolPreferenceList the List of students representing the preferences of a school
     * @return a Student object that is not yet assigned to a match or null if none was found
     */
    private Student findStudentInList(Set<Student> studentsNotMatched, List<Student> schoolPreferenceList) {
        for (var student : schoolPreferenceList)
            if (studentsNotMatched.contains(student))
                return student;
        return null;
    }

    /**
     * This method solves the problem returning a Solution object;
     *
     * @return a Solution object containing a correct matching.
     */
    public Solution solveProblem() {
        Solution solution = new Solution();     //create a new solution object where we store the matches
        var studentsLeftToAssignSchool = new HashSet<>(Set.copyOf(studentsSet));         //copy the set of students
        while (!studentsLeftToAssignSchool.isEmpty()) {     //until the set is empty
            for (var entry : schoolPreferences.entrySet())   //for every school
                if (entry.getKey().getCapacity() > 0) { //if the school's capacity is greater than 0
                    Student matchStudent = findStudentInList(studentsLeftToAssignSchool, entry.getValue());   //find a student of their preference that doesn't have a match yet
                    if (matchStudent != null) {     //if we found a student
                        solution.addSolution(matchStudent, entry.getKey()); //add a solution
                        entry.getKey().setCapacity(entry.getKey().getCapacity() - 1);
                        studentsLeftToAssignSchool.remove(matchStudent);
                    }
                }
        }
        return solution;
    }

}
