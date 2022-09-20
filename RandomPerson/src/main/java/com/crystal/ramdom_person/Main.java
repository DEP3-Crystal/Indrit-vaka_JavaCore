package com.crystal.ramdom_person;

import com.crystal.ramdom_person.dao.DataFromFile;
import com.crystal.ramdom_person.io.OutputManager;
import com.crystal.ramdom_person.services.PersonSelector;

import static com.crystal.ramdom_person.io.ConsoleColors.TEXT_BLUE;

public class Main {
    public static void main(String[] args) {
        OutputManager.hr();
        OutputManager.showMessage(TEXT_BLUE+"\t\tWelcome to the Random Person Selector Program. ");
        OutputManager.hr();

        PersonSelector personSelector = new PersonSelector(new DataFromFile());
        personSelector.showMenu();
    }
}
