package com.crystal.atm.dao;

import com.crystal.atm.model.person.Person;
import org.testng.annotations.Test;

import java.util.List;

public class DataAccessTest {
    DataAccess dataAccess = new DataFromMemory();
    @Test
    public void testGetPeople() {
        List<Person> people = dataAccess.getPeople();

    }

    @Test
    public void testGetPerson() {
    }

    @Test
    public void testSavePerson() {
    }

    @Test
    public void testSavePeople() {
    }
}