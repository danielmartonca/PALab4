package lab4.optional.student;


import lombok.Data;

import java.util.Objects;

/**
 * The Student class that stores a name and a capacity. It also implements Comparable interface to compare objects by capacity.
 * It has a constructor, getters, setters and toString using lombok.
 */
public @Data
class Student implements Comparable<Student> {

    private String name;
    private double score;

    /**
     * Constructor for Student object
     *
     * @param name  the name of the student
     * @param score the score of the student
     */
    public Student(String name, double score) {
        this.name = name;
        this.score = score;
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
}

