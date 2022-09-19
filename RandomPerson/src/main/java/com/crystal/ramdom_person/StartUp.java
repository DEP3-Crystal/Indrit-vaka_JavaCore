package com.crystal.ramdom_person;

import com.crystal.ramdom_person.dao.PersonDao;
import com.crystal.ramdom_person.io.InputManager;
import com.crystal.ramdom_person.io.OutputManager;
import com.crystal.ramdom_person.model.Person;
import com.crystal.ramdom_person.services.ChoseByPriority;
import com.crystal.ramdom_person.services.GameLogic;
import com.crystal.ramdom_person.utility.PersonUtility;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.crystal.ramdom_person.io.ConsoleColors.*;

public class StartUp {
    private static List<Person> PEOPLE = new ArrayList<>();
    public static LinkedList<Person> CHOOSES = new LinkedList<>();

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
                GameLogic gameManager = new ChoseByPriority();
                randomChooseMenu(gameManager);
            }
            case 2 -> OutputManager.showPeople(PEOPLE);
            case 3 -> OutputManager.showChooses(CHOOSES, PersonUtility.dataSource);
            case 4 -> managePeopleMenu();
            case 0 -> exit();
            default -> OutputManager.showErrMessage("Invalid option");
        }
        menu();
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
            case 2 -> PersonDao.removeOne(PEOPLE);
            case 3 -> PersonDao.addOne(PEOPLE);
            case 4 -> {
                OutputManager.showMessage("Are you sure you want to remove all people? y/" + TEXT_BLUE + "n", TEXT_RED);
                if (InputManager.getLetter(PersonUtility.scanner).equalsIgnoreCase("y")) {
                    PersonDao.removeAll(PEOPLE);
                    OutputManager.showMessage("All people where removed successfully", TEXT_BLUE);
                }
            }
            case 5 -> PersonUtility.dataSource.savePeople(PEOPLE);
            case 0 -> menu();
            default -> OutputManager.showErrMessage("Invalid Option");
        }
        managePeopleMenu();
    }

    public void randomChooseMenu(GameLogic manager) {
        String yn = TEXT_BLUE + "y" + TEXT_RESET + "/" + TEXT_RED + "n" + TEXT_RESET;
        String ans;
        if (PersonUtility.FIRST_RUN) {
            PersonUtility.FIRST_RUN = false;
            OutputManager.showMessage("Do you what to load the data from the preview Run? " + yn);
            ans = InputManager.getLetter(PersonUtility.scanner);

            //check if users wants to load the old data
            if (ans.equalsIgnoreCase("Y")) {
                CHOOSES = PersonUtility.dataSource.loadChosen(true);
            } else if (!ans.equalsIgnoreCase("n")) {
                OutputManager.showErrMessage("Invalid Option");
                randomChooseMenu(manager);
                return;
            }
        }

        do {
            Person chosenOne = manager.choseOne(PEOPLE, CHOOSES);
            OutputManager.showMessage(chosenOne.getFullName());

            OutputManager.showMessage("Do you want to make an other chose? " + yn + " 0. back to menu");
            ans = InputManager.getLetter(PersonUtility.scanner);
            if (ans.equalsIgnoreCase("0") || ans.equalsIgnoreCase("n"))
                menu();
            while (!ans.equalsIgnoreCase("n") && !ans.equalsIgnoreCase("y")) {
                OutputManager.showErrMessage("Invalid Option");
                ans = InputManager.getLetter(PersonUtility.scanner);
            }
        } while (ans.equalsIgnoreCase("y"));

    }

    public void exit() {
        System.exit(0);
    }
}