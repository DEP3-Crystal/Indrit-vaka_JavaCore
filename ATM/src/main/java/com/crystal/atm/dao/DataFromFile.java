package com.crystal.atm.dao;

import com.crystal.atm.model.person.Person;

import java.util.List;

public class DataFromFile implements  DataAccess{
    @Override
    public List<Person> getPeople() {
        return null;
    }

    @Override
    public Person getPerson(int id) {
        return null;
    }

    @Override
    public void savePerson(Person person) {

    }

    @Override
    public void savePeople(List<Person> people) {

    }
}
