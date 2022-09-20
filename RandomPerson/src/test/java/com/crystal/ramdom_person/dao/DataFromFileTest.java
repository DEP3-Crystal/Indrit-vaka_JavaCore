package com.crystal.ramdom_person.dao;

import com.crystal.ramdom_person.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class DataFromFileTest {

    @Test
    void loadChosen() {
//        DataSource dao = new DataFromFile();
//        LinkedList<Person> people = dao.loadChosen();
//        Assertions.assertEquals(5, people.size());
    }

    @Test
    void savePeople() {
        DataSource dao = new DataFromFile();
        List<Person> people = dao.loadPeople();
        Assertions.assertEquals(23, people.size());
    }
}