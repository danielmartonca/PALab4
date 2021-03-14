package lab4.compulsory.student;


import lombok.Data;

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


}

