package com.crystal.ramdom_person;

import com.crystal.ramdom_person.dao.DataFromJson;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * TODO
 *       1. Loading the people from a file, ✅
 *       2. adding new person at the beginning of the list not the end, ✅
 *       3. letting user to add, remove, override the person list
 * */
public class Test {
    static String[] PEOPLE;
    static boolean FIRST_RUN = true;
    public static LinkedList<String> CHOSEN = new LinkedList<>();

    public static void main(String[] args) {
        PEOPLE = DataFromJson.loadPeople();
        menu();
    }

    static void menu() {
        Scanner sc = new Scanner(System.in);
        String ans;
        do {
            System.out.println("""
                                        
                    Welcome!
                    1. Random choose
                    2. Show the peoples List
                    3. Show the chosen if there is any
                    0. Exit""");
            ans = sc.next().charAt(0) + "";

            switch (ans) {
                case "1" -> randomChooseMenu();
                case "2" -> showPeople(PEOPLE);
                case "3" -> showChooses();
                default -> System.err.println("not valid option");
            }
        } while (!ans.equals("0"));
    }

    public static void showPeople(String[] people) {
        Arrays.stream(people).forEach(Test::showName);
    }

    public static void showName(String email) {
        String name = email.split("@")[0];
        String firstName = name.split("\\.")[0];
        String lastName = name.split("\\.")[1];

        name = toCamelCase(firstName) + " " + toCamelCase(lastName);
        if (name.contains("-")) {
            name = Stream.of(name)
                    .map(n -> {
                        String[] parts = n.split("-");
                        return toCamelCase(parts[0]) + " " +
                                toCamelCase(parts[1]);
                    })
                    .collect(Collectors.joining());
        }
        System.out.println(name);
    }

    private static String toCamelCase(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }

    private static void showChooses() {
        //we want to ask only if is first run if they want to load from file
        if (FIRST_RUN) {
            System.out.println("Do you want to load data from previews run? Y/N");
            String ans = new Scanner(System.in).next().charAt(0) + "";
            if (ans.equals("y"))
                DataFromJson.loadChosen();
            FIRST_RUN = false;
        }
        CHOSEN.forEach(Test::showName);
    }

    public static void randomChooseMenu() {

        char ans;
        Scanner sc = new Scanner(System.in);
        if (FIRST_RUN) {
            FIRST_RUN = false;
            System.out.println("Do you what to load the data from the preview Run? y/n");
            ans = sc.next().charAt(0);

            //check if users wants to load the old data
            if (ans == 'y' || ans == 'Y') {
                DataFromJson.loadChosen();
            }
        }

        do {
            choseOne(PEOPLE);
            System.out.println("Do you want to make an other chose? Y/N  0. back to menu");
            ans = sc.next().toLowerCase().charAt(0);
            if (ans == '0')
                menu();

        } while (ans == 'y');

    }

    public static void choseOne(String[] people) {

        //1. make a random chose
        //2. Check if that has been chosen before
        //      3. If no show that, and add that to beginning of the chosen list
        //      4. In no make a random chose again based on the person priority
        //5. If we don't have any chose after step 4 se go again to step 1;


        //About priority
        //  the last person who enters the chosen list has the lowest priority to get chosen
        //          10% chance to get chosen
        // the priority for existing persons in the chosen lists increases when another one joins.
        //      The priority increment or the % to be chosen depends on the total list of users.
        //              lowest 10%
        //              Highest 100%
        //              90% will be deference between the lowest and highest, now based on the nr of people,
        //              we will increment the % it will increment by 90/length

        // Result of priority.
        //  the first person who enters the list will have 10% chance to get chosen and for each other person who
        //      joins the list his % will be increased by 80/users.length


        //Others
        //      If all the persons gets in the list the fist one who has joined the list will be removed.
        // We have the case when someone that is in the list of chosen and gets chosen again.
        // In this case this person will be moved the last one in the list, which means it has the lowest priority to get chosen

        //we make a random chose
        Random random = new Random();
        int randomChose = random.nextInt(people.length);

        //we check if this one has been chosen before
        if (CHOSEN.contains(people[randomChose])) {
            //1,2,3,4...10
            //1=> 10% chance to be chosen
            //10=> 100% chance to be chosen
            //length => 10
            //increment should be 90/10 => 9%
            //transforming the index to the priority
            //      so inc in this case will be 8% => 1*8,2*8,...10*8

            // The formula will be 100 - (90 / length * chosen index +1)
            //      lets take some ex suppose that len = 10
            //          1. for the st1 el of the list => 90 - (90 / 10 * 0)= 90 -(0 ) +1= 91=> 91 will be range of generation nr means 10 % for th nr to be 1
            //          2. str last el of list => 90 - (90/10 * 10) +1 = 90 - (90) +1 = 1 range if from 1-1 meaning 100% to get 1
            // With 90/length we will get the increment we need to increase the % from one person to other one. Ex. 90/20=4
            //

            int range = 90 - (90 / people.length * CHOSEN.indexOf(people[randomChose])) + 1;
            int n = random.nextInt(range);
            if (n == 1) {
                System.out.println(people[randomChose]);
                CHOSEN.removeIf(p -> p.equals(people[randomChose]));
                CHOSEN.addFirst(people[randomChose]);
                DataFromJson.saveChosen(CHOSEN);

            } else {
                choseOne(people);
            }
        } else {
            System.out.println(people[randomChose]);
            //we add the chosen
            CHOSEN.addFirst(people[randomChose]);

            //if all people have been chosen once we remove the last one
            if (CHOSEN.size() >= PEOPLE.length) {
                CHOSEN.removeLast();
            }

            DataFromJson.saveChosen(CHOSEN);

        }

    }

}