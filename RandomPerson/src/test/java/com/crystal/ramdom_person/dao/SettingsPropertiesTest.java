package com.crystal.ramdom_person.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SettingsPropertiesTest {

    @Test
    void setChoosesFilePath() {
        SettingsProperties.setChoosesFilePath("RandomPerson/src/main/resources/chosen.csv");
    }

    @Test
    void getChoosesFilePath() {
        String path = "RandomPerson/src/main/resources/chosen.csv";
        Assertions.assertEquals(path, SettingsProperties.getChoosesFilePath());
    }

    @Test
    void setPeopleFilePath() {
        SettingsProperties.setPeopleFilePath("RandomPerson/src/main/resources/people.csv");
    }

    @Test
    void getPeopleFilePath() {
        String path = "RandomPerson/src/main/resources/people.csv";
        Assertions.assertEquals(path, SettingsProperties.getPeopleFilePath());
    }

    @Test
    void firstRun() {
        SettingsProperties.cleanFirstRun();
        Assertions.assertTrue(SettingsProperties.firstRun());
        Assertions.assertFalse(SettingsProperties.firstRun());
    }
}