package lab4.optional.student;


import lab4.optional.school.School;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The Student class that stores a name and a capacity. It also implements Comparable interface to compare objects by capacity.
 * It has a constructor, getters, setters and toString using lombok.
 */
public @Data
class Student implements Comparable<Student> {

    private String name;
    private Map<School, Double> gradesAtExam;

    /**
     * Constructor for Student object
     *
     * @param name the name of the student
     */
    public Student(String name) {
        this.name = name;
        this.gradesAtExam = new HashMap<>();
    }

    /**
     * Method inherited from interface Comparable to compare objects
     *
     * @param o the student to compare to
     * @return int value by comparing names as strings.
     */
    @Override
    public int compareTo(Student o) {
        return name.compareTo(o.getName());
    }

    /**
     * Overridden equals method
     *
     * @param o the object to test equals with
     * @return true if objects are equal,false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return name.equals(student.name);
    }

    /**
     * Hash function for the object.
     *
     * @return the hash value
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * This method adds the grade at the exam for a specific school.
     *
     * @param school the school of which we set the grade
     * @param grade  the grade at the exam
     */
    public void setGradeForSchool(School school, double grade) {
        gradesAtExam.put(school, grade);
    }

    /**
     * This method returns the grade of the student at the exam at the specified school as parameter
     *
     * @param school the school at which he took the grade
     * @return the grade
     */
    public double getGradeForSchool(School school) {
        return gradesAtExam.get(school);
    }
}

