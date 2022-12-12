package com.raymundo.lab4;

import java.util.Date;

public class Person {

    private final long id;
    private final String name;
    private final Gender gender;
    private final Date birthDate;
    private final Division division;
    private final double salary;

    public Person(long id, String name, Gender gender, Date birthDate, Division division, double salary) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.division = division;
        this.salary = salary;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Division getDivision() {
        return division;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", birthDate=" + birthDate +
                ", division=" + division +
                ", salary=" + salary +
                '}';
    }
}
