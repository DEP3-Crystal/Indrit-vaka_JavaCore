package com.crystal.ramdom_person.services;

import com.crystal.ramdom_person.dao.DataSource;
import com.crystal.ramdom_person.dao.PersonDao;
import com.crystal.ramdom_person.io.InputManager;
import com.crystal.ramdom_person.io.OutputManager;
import com.crystal.ramdom_person.model.Person;
import com.crystal.ramdom_person.utility.PersonUtility;

import java.util.LinkedList;
import java.util.List;

import static com.crystal.ramdom_person.io.ConsoleColors.*;

public class PersonSelector {

    private final List<Person> people;
    private final DataSource dataSource;
    private final LinkedList<Person> chooses;

    public PersonSelector(DataSource dataSource) {
        chooses = dataSource.loadChosen();
        people = dataSource.loadPeople();
        this.dataSource = dataSource;
    }

    public void showMenu() {

        OutputManager.showMessage("""
                                    
                Welcome!
                1. Random choose
                2. Show the peoples List
                3. Show the chosen if there is any
                4. Manage People List
                0. Exit""");
        int ans;
        do {
            ans = InputManager.getInt(PersonUtility.scanner);
            handleAnswer(ans);
        } while (ans != 0);

    }

    private void handleAnswer(int ans) {
        switch (ans) {
            case 1 -> {
                randomChooseMenu();
            }
            case 2 -> OutputManager.showPeople(people);
            case 3 -> OutputManager.showChooses(chooses, PersonUtility.dataSource);
            case 4 -> managePeopleMenu();
            case 0 -> exit();
            default -> OutputManager.showErrMessage("Invalid option");
        }
        showMenu();
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
        managePeopleAnswerHandler(answer);
        managePeopleMenu();
    }

    private void managePeopleAnswerHandler(int answer) {
        switch (answer) {
            case 1 -> listAllPersons();
            case 2 -> PersonDao.removeOne(people);
            case 3 -> PersonDao.addOne(people);
            case 4 -> {
                OutputManager.showMessage("Are you sure you want to remove all people? y/" + TEXT_BLUE + "n", TEXT_RED);
                if (InputManager.getLetter(PersonUtility.scanner).equalsIgnoreCase("y")) {
                    PersonDao.removeAll(people);
                    OutputManager.showMessage("All people where removed successfully", TEXT_BLUE);
                }
            }
            case 5 -> dataSource.savePeople(people);
            case 0 -> showMenu();
            default -> {
                OutputManager.showErrMessage("Invalid Option");
                managePeopleMenu();
            }
        }
    }

    protected void listAllPersons() {


    }


    public void randomChooseMenu() {
        //TODO Let user to chose between different implementation
        GameLogic manager = new ChoseByPriority();

        String yn = TEXT_BLUE + "y" + TEXT_RESET + "/" + TEXT_RED + "n" + TEXT_RESET;
        String ans;
        if (PersonUtility.FIRST_RUN) {
            PersonUtility.FIRST_RUN = false;
            OutputManager.showMessage("Do you what to load the data from the preview Run? " + yn);
            ans = InputManager.getLetter(PersonUtility.scanner);

            //check if users wants to load the old data
            if (ans.equalsIgnoreCase("Y")) {
                dataSource.loadChosen();
            } else if (!ans.equalsIgnoreCase("n")) {
                OutputManager.showErrMessage("Invalid Option");
                randomChooseMenu();
                return;
            }
        }
        manager.choseOne(people, chooses);
        do {
            Person chosenOne = manager.choseOne(people, chooses);
            OutputManager.showMessage(chosenOne.getFullName());

            OutputManager.showMessage("Do you want to make an other chose? " + yn + " 0. back to menu");
            ans = InputManager.getLetter(PersonUtility.scanner);
            if (ans.equalsIgnoreCase("0") || ans.equalsIgnoreCase("n"))
                showMenu();
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
