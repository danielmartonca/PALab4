package lab4.optional.school;


import lombok.Data;

import java.util.Objects;

/**
 * The School class that stores a name and a capacity. It also implements Comparable interface to compare objects by capacity.
 * It has a constructor, getters and setters using lombok and toString for printing.
 */
public @Data
class School implements Comparable<School> {

    private String name;
    private int capacity;


    /**
     * Constructor
     *
     * @param name     the name of the school
     * @param capacity the capacity of the shcool
     */
    public School(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;

    }

    /**
     * Method inherited from interface Comparable to compare objects
     *
     * @param o the school to compare to
     * @return int value by comparing capacities.
     */
    @Override
    public int compareTo(School o) {
        return this.capacity - o.getCapacity();
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
        School school = (School) o;
        return name.equals(school.name);
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
