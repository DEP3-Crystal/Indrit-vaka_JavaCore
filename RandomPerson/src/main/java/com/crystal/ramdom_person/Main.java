package com.crystal.ramdom_person;

import com.crystal.ramdom_person.dao.DataFromFile;
import com.crystal.ramdom_person.io.OutputManager;
import com.crystal.ramdom_person.services.PersonSelector;

public class Main {
    public static void main(String[] args) {
        OutputManager.showMessage("Welcome to the Random Person Selector Program. Please choose an option from list below:");
        PersonSelector personSelector = new PersonSelector(new DataFromFile());
        personSelector.showMenu();
    }
}
