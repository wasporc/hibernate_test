package ru.hiber.dto;

import ru.hiber.entity.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonCreationDto{
    private List<Person> persons;

    public PersonCreationDto(List<Person> list) {
        this.persons = list;
    }

    public PersonCreationDto() {
        this(new ArrayList<>());
    }

    public void add(Person t) {
        persons.add(t);
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
