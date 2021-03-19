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
    private Map<Student, List<List<School>>> studentsPreferences;
    private Map<School, List<List<Student>>> schoolPreferences;

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
            studentsPreferences.get(student).add(new LinkedList<>(Arrays.asList(schools)));
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
            schoolPreferences.get(school).add(new LinkedList<>(Arrays.asList(students)));
    }


    // Methods from now on will be used to solve the problem using Gale Shapley Algorithm


    /**
     * This method sorts studentPreferences based on the sum of sizes in value's lists
     */
    private void sortStudentPreferencesByValueSize() {
        List<Pair<Student, List<List<School>>>> pairList = new ArrayList<>();
        for (var entry : studentsPreferences.entrySet())
            pairList.add(Pair.of(entry.getKey(), entry.getValue()));

        pairList.sort((o1, o2) -> {
            int totalSizeO1 = 0;
            for (var size : o1.getRight())
                totalSizeO1 += size.size();
            for (var size : o2.getRight())
                totalSizeO1 -= size.size();
            return totalSizeO1;
        });

        studentsPreferences = new LinkedHashMap<>();
        for (var pair : pairList)
            studentsPreferences.put(pair.getKey(), pair.getValue());

    }

    /**
     * This method creates a Map containing all School and Student objects in the sets and returns it.
     *
     * @return a map with all the objects in the sets and false
     */
    private Map<Object, Boolean> getMatchedMapEmpty() {
        Map<Object, Boolean> matchedAlready = new HashMap<>();
        for (var student : studentsSet)
            matchedAlready.put(student, false);
        for (var school : schoolsSet)
            matchedAlready.put(school, false);
        return matchedAlready;
    }

    /**
     * This method matches a student with a school.
     * First it adds a pair of < Student,School > in the parameter matchesList
     * Then it sets true for both the student and the School in the parameter matchedAlready
     * After that it removes the school from the preference list of the student.
     * Lastly, after the removal, if that list is empty it will be removed also from the list of lists.
     *
     * @param student                            the student to add
     * @param school                             the school to add
     * @param matchesList                        the matches list
     * @param matchedAlready                     the map with the students and schools who already have a match
     * @param studentSchoolPreferenceListOfLists the list of lists for the student
     */
    private void match(Student student, School school, List<Pair<Student, School>> matchesList, Map<Object, Boolean> matchedAlready, List<List<School>> studentSchoolPreferenceListOfLists) {
        matchesList.add(Pair.of(student, school));
        matchedAlready.put(student, true);
        matchedAlready.put(school, true);
    }

    /**
     * This method returns the last student matched with the school in school's preference list.
     *
     * @param schoolPreferencesList the school preference list.
     * @param matchedAlready        the list of students who are matched already
     * @return the student last student in the preference list who is matched
     */
    private Student getStudentMatchedInPreferencesForSchool(School school, List<List<Student>> schoolPreferencesList, Map<Object, Boolean> matchedAlready, List<Pair<Student, School>> matchesList) {
        Student lastStudentMatched = null;
        for (var list : schoolPreferencesList)
            for (var studentInList : list)
                if (matchedAlready.containsKey(studentInList))  //if it's matched
                    for (var pair : matchesList)
                        if (pair.getKey().equals(studentInList) && pair.getValue().equals(school)) {
                            lastStudentMatched = studentInList;
                            break;
                        }

        return lastStudentMatched;
    }

    /**
     * This method searches trough the schoolPreferencesList and checks if student1 is better than student2
     *
     * @param student1              the first student
     * @param student2              the second student
     * @param schoolPreferencesList the list to search through
     * @return true if student 1 is found earlier than student 2, false otherwise
     */
    private boolean isStudent1BetterThanStudent2(Student student1, Student student2, List<List<Student>> schoolPreferencesList) {
        for (var list : schoolPreferencesList)
            for (var studentInList : list) {
                if (studentInList == student1)
                    return true;
                if (studentInList == student2)
                    return false;
            }
        return false;
    }

    /**
     * This method removes the student from the matches list and sets false to it's boolean value in the map matchedAlready
     *
     * @param student        the student
     * @param matchedAlready the list of students and schools who are matched already
     * @param matchesList    the matches list
     */
    private void unmatch(Student student, Map<Object, Boolean> matchedAlready, List<Pair<Student, School>> matchesList) {
        matchedAlready.put(student, false);
        matchesList.removeIf(pair -> pair.getLeft().equals(student));
    }

    /**
     * This method solves the problem returning a Solution object;
     *
     * @return a Solution object containing a correct matching.
     */
    public Solution solveGaleShapleyAlgorithm() {
        Solution solution = new Solution();     //create a new solution object where we store the matches

        sortStudentPreferencesByValueSize();    //sort the student preferences map by the number of schools in it's preferences list
        var matchedAlready = getMatchedMapEmpty();  //create a map that stores keys of students and schools and values boolean
        var matchesList = new LinkedList<Pair<Student, School>>();  //the list where we will store the temporary matches. This list elements will change in time.

        while (matchesList.size() != studentsSet.size()) {      //until we've matched all students
            for (var studentsAndPreferences : studentsPreferences.entrySet()) {       //Get the next student and it's preferences list
                var student = studentsAndPreferences.getKey();                //Store the student in variable student
                if (!matchedAlready.get(student))   //if the student isn't matched already
                {
                    var school = studentsAndPreferences.getValue().get(0).get(0); //Get the first school in the list of lists of it's preferences
                    if (school.getCapacity() > 0)  // check if the it's capacity is higher than 0
                    {
                        match(student, school, matchesList, matchedAlready, studentsAndPreferences.getValue()); //if yes, match them
                        school.setCapacity(school.getCapacity() - 1);   //decrease the school's capacity
                    } else {
                        var studentToCompare = getStudentMatchedInPreferencesForSchool(school, schoolPreferences.get(school), matchedAlready, matchesList);    //get last student matched in the list of preferences for the school
                        if (isStudent1BetterThanStudent2(student, studentToCompare, schoolPreferences.get(school)))   //if the student is better than the one already matched
                        {
                            unmatch(studentToCompare, matchedAlready, matchesList);  //un-match the old student
                            match(student, school, matchesList, matchedAlready, studentsAndPreferences.getValue());  //match the new one
                        }
                    }
                    studentsAndPreferences.getValue().get(0).remove(school);    //remove the school from student's preferences
                    if (studentsAndPreferences.getValue().get(0).size() == 0)   //if the list is empty
                        studentsAndPreferences.getValue().remove(studentsAndPreferences.getValue().get(0));//remove it
                }
            }
        }
        for (var pair : matchesList)
            solution.addSolution(pair.getKey(), pair.getValue());
        return solution;
    }

}
