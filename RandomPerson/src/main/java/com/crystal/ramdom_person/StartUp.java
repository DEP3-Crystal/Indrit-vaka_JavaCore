package com.crystal.ramdom_person;

import com.crystal.ramdom_person.io.InputManager;
import com.crystal.ramdom_person.io.OutputManager;
import com.crystal.ramdom_person.model.Person;
import com.crystal.ramdom_person.utility.PersonUtility;
import com.crystal.ramdom_person.validator.Validator;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static com.crystal.ramdom_person.io.ConsoleColors.*;

public class StartUp {
    private static List<Person> PEOPLE;
    public static LinkedList<Person> CHOSEN = new LinkedList<>();

    public static void main(String[] args) {
        PEOPLE = PersonUtility.dataSource.loadPeople();
        new StartUp().menu();
    }
    public void menu() {

        OutputManager.showMessage("""
                                    
                Welcome!
                1. Random choose
                2. Show the peoples List
                3. Show the chosen if there is any
                4. Manage People List
                0. Exit""");
        int ans = InputManager.getInt(PersonUtility.scanner);

        switch (ans) {
            case 1 -> {
                GameManager gameManager = new GameManager();
                randomChooseMenu(gameManager);
            }
            case 2 -> OutputManager.showPeople(PEOPLE);
            case 3 -> OutputManager.showChooses(CHOSEN, PersonUtility.dataSource);
            case 4 -> managePeopleMenu();
            case 0 -> exit();
            default -> OutputManager.showErrMessage("Invalid option");
        }
        menu();
    }

    public void randomChooseMenu(GameManager manager) {
        String yn = TEXT_BLUE + "y" + TEXT_RESET + "/" + TEXT_RED + "n" + TEXT_RESET;
        String ans;
        if (PersonUtility.FIRST_RUN) {
            PersonUtility.FIRST_RUN = false;
            OutputManager.showMessage("Do you what to load the data from the preview Run? " + yn);
            ans = InputManager.getLetter(PersonUtility.scanner);

            //check if users wants to load the old data
            if (ans.equalsIgnoreCase("Y")) {
               CHOSEN = PersonUtility.dataSource.loadChosen();
            } else if (!ans.equalsIgnoreCase("n")) {
                OutputManager.showErrMessage("Invalid Option");
                randomChooseMenu(manager);
                return;
            }
        }

        do {
            manager.choseOne(PEOPLE, CHOSEN);

            OutputManager.showMessage("Do you want to make an other chose? " + yn + " 0. back to menu");
            ans = InputManager.getLetter(PersonUtility.scanner);
            if (ans.equalsIgnoreCase("0")||ans.equalsIgnoreCase("n"))
                menu();
            while (!ans.equalsIgnoreCase("n") && !ans.equalsIgnoreCase("y")) {
                OutputManager.showErrMessage("Invalid Option");
                ans = InputManager.getLetter(PersonUtility.scanner);
            }
        } while (ans.equalsIgnoreCase("y"));

    }

    public void managePeopleMenu() {
        OutputManager.showMessage("""
                                    
                1. To show people list
                2. To remove one
                3. To add one
                4. To Remove All
                5. To save
                0. Go back to main menu
                """);
        int answer = InputManager.getInt(PersonUtility.scanner);
        switch (answer) {
            case 1 -> OutputManager.showPeople(PEOPLE);
            case 2 -> removeOne();
            case 3 -> addOne();
            case 4 -> removeAll();
            case 5 -> PersonUtility.dataSource.savePeople(PEOPLE);
            case 0 -> menu();
            default -> OutputManager.showErrMessage("Invalid Option");
        }
        managePeopleMenu();
    }

    private void removeAll() {
        OutputManager.showMessage("Are you sure you want to remove all people? y/" + TEXT_BLUE + "n", TEXT_RED);
        if (InputManager.getLetter(PersonUtility.scanner).equalsIgnoreCase("y")) {
            PEOPLE.clear();
            OutputManager.showMessage("All people where removed successfully", TEXT_BLUE);
        }

    }

    private void addOne() {
        OutputManager.showMessage("0. to go back to menu:  \n" + TEXT_BLUE + "Please give the person email you want to add");
        String inputtedEmail = new Scanner(System.in).nextLine();

        if (Validator.validEmail(inputtedEmail)) {
            PEOPLE.add(new Person(inputtedEmail));
            System.out.println("Added successfully");
            addOne();
        } else if (inputtedEmail.equals("0")) {
            managePeopleMenu();
        } else {
            OutputManager.showErrMessage("Not a valid email");
            addOne();
        }
    }

    private void removeOne() {
        OutputManager.showMessage("Please type the person nr or the full name of the person you want to remove \n " + TEXT_BLUE + "0. to show people list");
        String ans = InputManager.getWordString(PersonUtility.scanner);
        try {
            int index = Integer.parseInt(ans) - 1;

            if (index == -1) {
                OutputManager.showPeople(PEOPLE);
                removeOne();
            } else if (index < 0 || index > PEOPLE.size()) {
                System.err.println("You have given invalid nr");
                removeOne();
            } else {
                PEOPLE.remove(index);
                OutputManager.showMessage("removed successfully");
            }
        } catch (Exception e) {
            //if person won't be removed show a message
            if (PEOPLE.removeIf(p -> p.getFullName().equalsIgnoreCase(ans))) {
                OutputManager.showMessage("removed successfully");

            } else {
                OutputManager.showErrMessage("You have given invalid name");
                removeOne();
            }
        }
        OutputManager.hr();
        managePeopleMenu();
    }

    public void exit() {
        System.exit(0);
    }
}