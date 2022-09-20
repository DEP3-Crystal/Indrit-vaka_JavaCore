package com.crystal.ramdom_person.old;

import com.crystal.ramdom_person.dao.old.DataFromJson;
import com.crystal.ramdom_person.validator.Validator;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//TODO:
// 1. IO
// 2. Model, Interface
// 3. DAO => IDataSource -> Memory, FileSource, DataBaseSource;
// 4. Utility
public class StartUp {
    static List<String> PEOPLE;
    static boolean FIRST_RUN = true;
    public static LinkedList<String> CHOSEN = new LinkedList<>();

    public static void main(String[] args) {
        PEOPLE = DataFromJson.loadPeople();
        menu();

//       showPeople(PEOPLE);
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
                    4. Manage People List
                    0. Exit""");
            ans = sc.next().charAt(0) + "";

            switch (ans) {
                case "1" -> randomChooseMenu();
                case "2" -> showPeople(PEOPLE);
                case "3" -> showChooses();
                case "4" -> managePeopleMenu();
                default -> System.err.println("not valid option");
            }
        } while (!ans.equals("0"));
    }

    private static void managePeopleMenu() {
        String ans;

        do {
            Scanner in = new Scanner(System.in);
            hr();
            System.out.println("""
                                        
                    1. To show people list
                    2. To remove one
                    3. To add one
                    4. To Remove All
                    5. To save
                    0. Go back to main menu
                    """);

            ans = in.next().charAt(0) + "";
            switch (ans) {
                case "1" -> showPeople(PEOPLE);
                case "2" -> removeOne();
                case "3" -> addOne();
                case "4" -> removeAll();
                case "5" -> DataFromJson.savePeople(PEOPLE);
                case "0" -> menu();
                default -> System.err.println("not valid option!");
            }
        } while (!ans.equalsIgnoreCase("0"));
    }

    private static void removeAll() {
        System.err.println("Are you sure you want to remove all people? y/n");
        if (new Scanner(System.in).next().equalsIgnoreCase("y")) {
            PEOPLE.clear();
            System.out.println("All people where removed successfully");
        }

    }

    private static void addOne() {
        System.out.println("0. to go back to menu:  \nPlease give the person email you want to add");
        String inputtedEmail = new Scanner(System.in).nextLine();
        if (Validator.validEmail(inputtedEmail)) {
            PEOPLE.add(inputtedEmail);
            System.out.println("Added successfully");
            addOne();
        } else if (inputtedEmail.equals("0")) {
            managePeopleMenu();
        } else {
            System.err.println("Not a valid email");
            addOne();
        }
    }

    private static void removeOne() {
        System.out.println("Please type the person nr or the person name you want to remove \n 0. to show people list");
        String ans = new Scanner(System.in).nextLine();
        try {
            int index = Integer.parseInt(ans) - 1;

            if (index == -1) {
                showPeople(PEOPLE);
                removeOne();
            } else if (index < 0 || index > PEOPLE.size()) {
                System.err.println("You have given invalid nr");
                removeOne();
            } else {
                PEOPLE.remove(index);
                System.out.println("removed successfully");
            }
        } catch (Exception e) {
            //if person won't be removed show a message
            if (!PEOPLE.removeIf(p -> getName(p).equalsIgnoreCase(ans))) {
                System.err.println("You have given invalid name");
                removeOne();
            }
        }

        hr();
        managePeopleMenu();
    }


    public static void showPeople(List<String> people) {
        int count = 1;
        for (String person : people) {
            System.out.println(count + ". " + getName(person));
            count++;
        }
        hr();
    }


    public static String getName(String email) {
        String name = email.split("@")[0];

        if (name.contains(".")) {

            String firstName = name.split("\\.")[0];
            String lastName = name.split("\\.")[1];

            name = toCamelCase(firstName) + " " + toCamelCase(lastName);
        }
        if (name.contains("-")) {
            name = Stream.of(name)
                    .map(n -> {
                        String[] parts = n.split("-");
                        return toCamelCase(parts[0]) + " " +
                                toCamelCase(parts[1]);
                    })
                    .collect(Collectors.joining());
        }
        return name;
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
                CHOSEN = DataFromJson.loadChosen();
            FIRST_RUN = false;
        }
        CHOSEN.forEach(p -> System.out.println(getName(p)));
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

    public static void choseOne(List<String> people) {

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
        int randomChose = random.nextInt(people.size());

        //we check if this one has been chosen before
        if (CHOSEN.contains(people.get(randomChose))) {
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

            int range = 90 - (90 / people.size() * CHOSEN.indexOf(people.get(randomChose))) + 1;
            int n = random.nextInt(range);
            if (n == 1) {
                System.out.println(people.get(randomChose));
                CHOSEN.removeIf(p -> p.equals(people.get(randomChose)));
                CHOSEN.addFirst(people.get(randomChose));
                DataFromJson.saveChosen(CHOSEN);

            } else {
                choseOne(people);
            }
        } else {
            System.out.println(people.get(randomChose));
            //we add the chosen
            CHOSEN.addFirst(people.get(randomChose));

            //if all people have been chosen once we remove the last one
            if (CHOSEN.size() >= PEOPLE.size()) {
                CHOSEN.removeLast();
            }

            DataFromJson.saveChosen(CHOSEN);

        }

    }

    public static void hr() {
        System.out.println("------------------------------------");
    }
}