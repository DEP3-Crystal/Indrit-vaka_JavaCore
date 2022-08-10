package com.crystal.ramdom_person;

import java.io.File;
import java.io.FileWriter;
import java.util.*;
/*
* TODO
*       1. Loading the people from a file,
*       2. adding new person at the beginning of the list not the end,
*       3. letting user to add, remove, override the person list
* */
public class Main
{
    static String[] PEOPLE;
    static boolean firstRun = true;
    public static List<String> CHOSEN = new ArrayList<>();
    public static void main(String[] args)
    {
        PEOPLE = new String[]{
                "isuf.muca@crystal-system.eu",
                "danjel.halili@crystal-system.eu",
                "flavio.lorenci@crystal-system.eu",
                "elia.omeri@crystal-system.eu",
                "ardit.elezi@crystal-system.eu",
                "luka.buziu@crystal-system.eu",
                "megi.lala@crystal-system.eu",
                "irena.shahini@crystal-system.eu",
                "indrit.vaka@crystal-system.eu",
                "griselda.muci@crystal-system.eu",
                "theodor.gheorghe@crystal-system.eu",
                "ioan.cocianu@crystal-system.eu",
                "teofil.mitrea@crystal-system.eu",
                "eduard.tiutiu@crystal-system.eu",
                "george.dobrota@crystal-system.eu",
                "stefanita.plesa@crystal-system.eu",
                "adrian-nicolae.tigau@crystal-system.eu",
                "emanuel.grabovschi@crystal-system.eu",
                "george.sirbu@crystal-system.eu",
                "andrei.state@crystal-system.eu",
                "florin-adrian.dumitru@crystal-system.eu",
                "victor.hincu@crystal-system.eu"
        };
        menu();
    }


    static void menu()
    {
        Scanner sc = new Scanner(System.in);
        String ans;
        do
        {
            System.out.println("""
                                        
                                        
                    Welcome!
                    1. Random choose
                    2. Show the peoples List
                    3. Show the chosen if there is any
                    0. Exit""");
            ans = sc.next().charAt(0) + "";

            switch (ans) {
                case "1" -> randomChooseMenu();
                case "2" -> showPeoples();
                case "3" -> showChosen();
            }
        } while (!ans.equals("0"));
    }

    private static void showPeoples()
    {
        Arrays.stream(PEOPLE).forEach(System.out::println);
    }

    private static void showChosen()
    {
        //we want to ask only if is first run if they want to load from file
        if (firstRun)
        {
            System.out.println("Do you want to load data from previews run? Y/N");
            String ans = new Scanner(System.in).next().charAt(0) + "";
            if (ans.equals("y"))
                loadData();
            firstRun = false;
        }
        //First we remove the empty ones (X) dummy data and then show the list
        CHOSEN.stream().filter(c -> !c.equals("X")).forEach(System.out::println);
    }
    //Exercise

    public static void randomChooseMenu()
    {

        char ans;
        Scanner sc = new Scanner(System.in);
        if (firstRun)
        {
            firstRun = false;
            System.out.println("Do you what to load the data from the preview Run? y/n");
            ans = sc.next().charAt(0);

            //check if users wants to load the old data
            if (ans == 'y' || ans == 'Y')
            {
                loadData();
            }
            else
            {
                //if the user doesn't want to, we fill the list with some data for the priority reasons
                fillChosen(PEOPLE.length);
            }
        }

        do
        {
            choseOne(PEOPLE);
            System.out.println("Do you want to make an other chose? Y/N  0. back to menu");
            ans = sc.next().toLowerCase().charAt(0);
            if (ans == '0')
                menu();
        } while (ans == 'y');

    }

    static void loadData()
    {
        //check if we have some data
        File file = new File("data.txt");
        //clean the chooses
        CHOSEN.clear();
        if (file.exists())
        {
            try
            {
                Scanner reader = new Scanner(file);
                while (reader.hasNextLine())
                {
                    CHOSEN.add(reader.nextLine());
                }
            } catch (Exception e)
            {
                System.out.println("There was a problem while we try to read : " + e.getMessage());
            }
        }
        else if (CHOSEN.size() == 0)
        {
            //if not we fill the list if it's empty with some data for the priority reasons
            fillChosen(PEOPLE.length);
        }
    }
    public static void fillChosen(int length)
    {
        //if not we fill the list with some data for the priority reasons
        for (int i = 0; i < length; i++)
        {
            CHOSEN.add("X");
        }
    }

    public static void choseOne(String[] users)
    {

        //1. make a random chose
        //2. Check if that has been chosen before
        //      3. If no show that, and add that to the chosen list
        //      4. In no make a random chose again based on the person priority
        //4. If we don't have any chose after step 3 se go again to step 1;


        //About priority
        //  the last person who enters the chosen list has the lowest priority to get chosen
        //          10% chance to get chosen
        // the priority for existing persons in the chosen lists increases when another one joins.
        //      The priority increment or the % to be chosen depends on the total list of users.
        //              lowest 10%
        //              Highest 90%
        //              80% will be deference between the lowest and highest, now based on the nr of people,
        //              we will increment the % it will increment by 80/length

        // Result of priority.
        //  the first person who enters the list will have 10% chance to get chosen and for each other person who
        //      joins the list his % will be increased by 80/users.length


        //Others
        //      If all the persons gets in the list the fist one who has joined the list will be removed.
        //We have the case when someone that is in the list of chosen and gets chosen again.
        // In this case this person will be moved the last one in the list, which means it has the lowest priority to get chosen

        //we make a random chose
        Random random = new Random();
        int randomChose = random.nextInt(users.length);

        //we check if this one has been chosen before
        if (CHOSEN.contains(users[randomChose]))
        {
            //1,2,3,4...10
            //1=>90% to be chosen
            //20=> 10% to be chosen
            //length => 10
            //increment should be 80/10 =>8%
            //transforming the index to the priority
            //      so inc in this case will be 8% => 1*8,2*8,...10*8

            //Make another random number based on the priority formula deference in % /length * index of chosen one
            //With 80/length we will get the increment we need to increase the %. Ex. 80/20=4
            //We need to have a filled list with some dummy content, to make the index work as expected.


            int range = 90 / users.length * CHOSEN.indexOf(users[randomChose]) + 1;
            int n = random.nextInt(range);
            if (n == 1)
            {
                System.out.println(users[randomChose]);
                CHOSEN.removeIf(p -> p.equals(users[randomChose]));
                CHOSEN.add(users[randomChose]);
                saveData();
            }
            else
            {
                choseOne(users);
            }
        }
        else
        {
            System.out.println(users[randomChose]);
            //we add the chosen
            CHOSEN.add(users[randomChose]);
            //remove the first el that has entered the list
            CHOSEN.remove(0);
            saveData();
        }

    }

    public static void saveData()
    {
        try
        {
            FileWriter file = new FileWriter("data.txt");
            String content = "";
            for (String choose : CHOSEN)
            {
                content += choose + "\n";
            }
            file.write(content);
            file.close();
        } catch (Exception e)
        {
            System.out.println("There was a problem during saving the file: " + e.getMessage());
        }
    }
}