package lab4.compulsory.student;


import lombok.Data;

public @Data
class Student implements Comparable<Student> {

    private String name;


    public Student(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Student o) {
        return name.compareTo(o.getName());
    }


    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }

}

