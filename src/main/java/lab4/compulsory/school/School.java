package lab4.compulsory.school;


import lombok.Data;


public @Data
class School implements Comparable<School> {

    private String name;
    private int capacity;


    public School(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;

    }

    @Override
    public int compareTo(School o) {
        return this.capacity - o.getCapacity();
    }

    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                '}';
    }

}
