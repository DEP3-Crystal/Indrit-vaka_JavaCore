package com.crystal.ramdom_person.dao;

import com.crystal.ramdom_person.io.InputManager;
import com.crystal.ramdom_person.io.OutputManager;
import com.crystal.ramdom_person.model.Person;
import com.crystal.ramdom_person.utility.PersonUtility;
import com.crystal.ramdom_person.validator.Validator;

import java.util.List;

import static com.crystal.ramdom_person.io.ConsoleColors.TEXT_BLUE;

public class PersonDao {

    public static void removeAll(List<Person> people) {
            people.clear();
    }
    public static void addOne(List<Person> people) {
        OutputManager.showMessage("0. to go back to menu:  \n" + TEXT_BLUE + "Please give the person email you want to add");
        String inputtedEmail = InputManager.getString(PersonUtility.scanner);

        if (Validator.validEmail(inputtedEmail)) {
            people.add(new Person(inputtedEmail));
            OutputManager.showMessage("Added successfully");
            addOne(people);
        } else if (inputtedEmail.equals("0")) {
            return;
        } else {
            OutputManager.showErrMessage("Not a valid email");
            addOne(people);
        }
    }

    public static void removeOne(List<Person> people) {
        OutputManager.showMessage("Please type the person nr or the full name of the person you want to remove \n "
                + TEXT_BLUE + "\t1. to show people list\n\t0. Mennu");
        String ans = InputManager.getLetter(PersonUtility.scanner);
        try {
            int index = Integer.parseInt(ans) - 1;

            if (index +1 == 1) {
                OutputManager.showPeople(people);
                removeOne(people);

            } else if (index +1 ==0 ) {
                return;
            } else if (index < 0 || index > people.size()) {
                System.err.println("You have given invalid nr");
                removeOne(people);
            } else {
                people.remove(index);
                OutputManager.showMessage("removed successfully");
            }
        } catch (Exception e) {
            //if person won't be removed show a message
            if (people.removeIf(p -> p.getFullName().equalsIgnoreCase(ans))) {
                OutputManager.showMessage("removed successfully");

            } else {
                OutputManager.showErrMessage("You have given invalid name");
                removeOne(people);
            }
        }
        OutputManager.hr();
    }

}
