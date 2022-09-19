package com.crystal.ramdom_person.dao;

import com.crystal.ramdom_person.model.Person;

import java.util.LinkedList;
import java.util.List;

public interface DataSource {
    LinkedList<Person> loadChosen();
    LinkedList<Person> loadChosen(boolean loadTimeOfChosen);

    List<Person> loadPeople();

    void saveChosen(List<Person> chosen);

    void savePeople(List<Person> people);
}
