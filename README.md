# Laboratory 4

At the last laboratory I finished the tasks Optional and Compulsory.

This repository is for the tasks given at laboratory 4. I managed to finish .... so far.

## Essential tools

You need to have Java RE or JDK >= 10 installed on your computer.

## How to run it?

1. Open project with IntelliJ IDEA

Clone this repository and open it with IntelliJ IDEA. Build this project
(shortcut Ctrl+F9) then go to Run->Run...(shortcut Alt+Shift+F10) and select the file you want to run.

2. Compile and run the programs using CMD

Go to the folder where the file is located. Open a new terminal here.

If you want to compile the file Main.java you must type the command.

```bash
javac -d . Main.java
```

After you compiled it, if you want to start the program you can type the following command

```bash
java packageName.Main
```

where to packageName is the name of the package which includes the file Main.java.

## Compulsory

The task consists of:<br />

- [X] Create an object-oriented model of the problem. You should have at least the following classes: Student, School
  and the main class.
- [X] Create all the objects in the example using streams.
- [X] Create a list of students, using LinkedList implementation. Sort the students, using a comparator.
- [X] Create a set of schools, using a TreeSet implementation. Make sure that School objects are comparable.
- [X] Create two maps (having different implementations) describing the students and the school preferences and print
  them on the screen.

Here is the output I received:<br />

```dif
Students map: 
Student{name='S0'}=[School{name='H0', capacity=1}, School{name='H1', capacity=2}, School{name='H2', capacity=2}]
Student{name='S1'}=[School{name='H0', capacity=1}, School{name='H1', capacity=2}, School{name='H2', capacity=2}]
Student{name='S2'}=[School{name='H0', capacity=1}, School{name='H1', capacity=2}]
Student{name='S3'}=[School{name='H0', capacity=1}, School{name='H1', capacity=2}]


Schools map: 
School{name='H1', capacity=2}=[Student{name='S0'}, Student{name='S2'}, Student{name='S1'}]
School{name='H2', capacity=2}=[Student{name='S0'}, Student{name='S1'}, Student{name='S3'}]
School{name='H0', capacity=1}=[Student{name='S3'}, Student{name='S0'}, Student{name='S1'}, Student{name='S2'}]
```

## Optional

- [X] Create a class that describes the problem and one that describes a solution (a matching) to this problem.
- [X] Using Java Stream API, write queries that display the students who find acceptable a given list of schools, and
  the schools that have a given student as their top preference.
- [X] Use a third-party library in order to generate random fake names for students and schools.
- [X] Implement an algorithm for creating a matching, considering that each student has a score obtained at the
  evaluation exam, and the schools rank students based on this score.
- [X] Test your algorithm.

The output is different because of Math.rand() function used. <br />
Here is the output I received when i ran the code:<br />

```dif
The list of preferences is: [School(name=The Romaguera College, capacity=1), School(name=South New Jersey University, capacity=1), School(name=Southern Mayert College, capacity=2)]

The student "Lavada Ernser" accepts the list of preferences.
The student "Reed Hahn" accepts the list of preferences.


The list of schools who have student "Lavada Ernser" as their top target is:
The school "The Romaguera College".
The school "South New Jersey University".

Found the fallowing matching for the problem:
[ (Lavada Ernser:The Romaguera College), (Lorean Hermann:Southern Mayert College), (Mrs. Serafina Stehr:South New Jersey University), (Reed Hahn:Southern Mayert College), ].

Process finished with exit code 0
```

## Bonus

- [X] Consider the case in which a school can rank the students based on their specific criteria.
- [X] Implement the Gale Shapley algorithm in order to find a stable matching.
- [X] Consider the case in which preferences are not necessarily strict. Some consecutive preferences in an element's list may form a tie.
- [X] For example S1: H1, \[H2,H3] means that S1 prefers H1 over H2 and H3, but H2 and H3 have no precedence one over the other.
- [ ] Prove that in the case of SAP with ties, a problem may have multiple stable matchings, not all of the same size.
- [ ] Check out other examples of matching in practice.

Here is the output I received when i ran the code:<br />
```dif

Found the fallowing matching for the problem:
[ (S0:H0), (S1:H1), (S2:H1), (S3:H2), ].
Process finished with exit code 0
```

