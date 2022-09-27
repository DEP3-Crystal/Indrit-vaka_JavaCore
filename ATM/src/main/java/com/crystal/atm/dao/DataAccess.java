package com.crystal.atm.dao;

import com.crystal.atm.model.account.Card;
import com.crystal.atm.model.person.Person;

import java.util.List;
import java.util.Map;

public interface DataAccess {
    Map<Integer,Person> getPeople();

    Person getPerson(int id);

    void savePerson(Person person);

    void savePeople(List<Person> people);
    Map<String, Card> getCards();
}
