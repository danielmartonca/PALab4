package lab4.bonus.sap;

import com.google.common.collect.Multimap;
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
    private Map<Student, List<Set<School>>> studentsPreferences;
    private Map<School, List<Set<Student>>> schoolPreferences;

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
     * This method adds tie preferences to one student. In the schools variadic parameter we store schools
     * who have the same preference order for the student specified
     *
     * @param student the student to add preferences too
     * @param schools variadic parameter representing it's preferences
     */
    public void addPreferenceForStudent(Student student, School... schools) {
        if (!studentsSet.contains(student)) {
            System.err.println("The student doesnt exist in the students list of the problem.Add him/her first before adding his/her preferences.");
        } else
            studentsPreferences.get(student).add(new HashSet<>(Arrays.asList(schools)));
    }

    /**
     * This method adds tie preferences to a school. In the students variadic parameter we store students
     * who have the same preference order for the school specified
     *
     * @param school   the student to add preferences too
     * @param students variadic parameter representing it's preferences
     */
    public void addPreferenceForSchool(School school, Student... students) {

        if (!schoolsSet.contains(school)) {
            System.err.println("The school doesnt exist in the schools list of the problem.Add it first before adding it's preferences.");
        } else
            schoolPreferences.get(school).add(new HashSet<>(Arrays.asList(students)));
    }


    private void sortStudentPreferencesByValueSize() {
        List<Pair<Student, List<Set<School>>>> pairList = new ArrayList<>();
        for (var entry : studentsPreferences.entrySet())
            pairList.add(Pair.of(entry.getKey(), entry.getValue()));

        pairList.sort(new Comparator<Pair<Student, List<Set<School>>>>() {
            @Override
            public int compare(Pair<Student, List<Set<School>>> o1, Pair<Student, List<Set<School>>> o2) {
                int totalSizeO1 = 0;
                for (var size : o1.getRight())
                    totalSizeO1 += size.size();
                for (var size : o2.getRight())
                    totalSizeO1 -= size.size();
                return totalSizeO1;
            }
        });

        studentsPreferences = new LinkedHashMap<>();
        for (var pair : pairList)
            studentsPreferences.put(pair.getKey(), pair.getValue());

    }


    /**
     * This method solves the problem returning a Solution object;
     *
     * @return a Solution object containing a correct matching.
     */
    public Solution solveGaleShapleyAlgorithm() {
        Solution solution = new Solution();     //create a new solution object where we store the matches

        sortStudentPreferencesByValueSize();

        for (var entry : studentsPreferences.entrySet())
            System.out.println(entry.getKey().getName());
        return solution;
    }

}
