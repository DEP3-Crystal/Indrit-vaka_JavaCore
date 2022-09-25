package com.crystal.atm.dao;

import com.crystal.atm.model.person.Person;

import java.util.List;

public interface DataAccess {
    List<Person> getPeople();
    Person getPerson(int id);
    void savePerson(Person person);
    void savePeople(List<Person> people);

}
