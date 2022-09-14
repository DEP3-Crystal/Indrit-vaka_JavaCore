package com.crystal.ramdom_person.io;

import com.crystal.ramdom_person.dao.DataSource;
import com.crystal.ramdom_person.model.Person;
import com.crystal.ramdom_person.utility.PersonUtility;

import java.util.List;

public class OutputManager {
    public static void showPeople(List<Person> people) {
        int count = 1;
        for (Person person : people) {
            showMessage(count + ". " + person.getFullName());
            count++;
        }
        hr();
    }

    public static void showChooses(List<Person> chosen, DataSource dataSource) {
        //we want to ask only if is first run if they want to load from file
        if (PersonUtility.FIRST_RUN) {
            showMessage("Do you want to load data from previews run? Y/N");
            String ans = InputManager.getLetter(PersonUtility.scanner);
            if (ans.equalsIgnoreCase("y"))
                chosen = dataSource.loadChosen();
            PersonUtility.FIRST_RUN = false;
        }
        chosen.forEach(person -> showMessage(person.getFullName()));
    }

    public static void showMessage(String message) {
        System.out.println(message + ConsoleColors.TEXT_RESET);
    }

    public static void showMessage(String message, String color) {
        System.out.println(color + message + ConsoleColors.TEXT_RESET);
    }

    public static void showErrMessage(String message) {
        System.out.println(ConsoleColors.TEXT_RED + message);
        hr();
    }

    public static void hr() {
        System.out.println(ConsoleColors.TEXT_RESET + "====================================================================" + ConsoleColors.TEXT_RESET);
    }
}
