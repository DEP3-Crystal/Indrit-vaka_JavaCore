package com.crystal.atm.dao;

import com.crystal.atm.model.account.Card;
import com.crystal.atm.model.person.Person;

import java.util.List;
import java.util.Map;

public class DataFromFile implements DataAccess {
    @Override
    public Map<Integer, Person> getPeople() {
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

    @Override
    public Map<String, Card> getCards() {
        return null;
    }
}
