package com.crystal.atm.dao;

import com.crystal.atm.model.user.User;
import org.testng.annotations.Test;

import java.util.List;

public class DataAccessTest {
    DataAccess dataAccess = new DataFromMemory();
    @Test
    public void testGetPeople() {
        List<User> people = dataAccess.getUsers();

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