package d9.reflection;

import d9.reflection.anotation.Column;
import d9.reflection.anotation.Table;

@Table(name = "person")
public class Person {
    @Column(isPk=true)
    private int id;

    @Column(name = "person_name")
    private String name;

    @Column
    private double salary;

    public Person(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }


}
