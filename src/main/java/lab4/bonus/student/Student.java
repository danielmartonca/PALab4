package lab4.bonus.student;


import lab4.bonus.school.School;
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

    /**
     * Constructor for Student object
     *
     * @param name the name of the student
     */
    public Student(String name) {
        this.name = name;
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

