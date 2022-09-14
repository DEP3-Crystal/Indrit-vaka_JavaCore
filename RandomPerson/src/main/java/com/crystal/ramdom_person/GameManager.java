package com.crystal.ramdom_person;

import com.crystal.ramdom_person.io.OutputManager;
import com.crystal.ramdom_person.model.Person;
import com.crystal.ramdom_person.utility.PersonUtility;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class GameManager {

    public void choseOne(List<Person> people, LinkedList<Person> chosen) {

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
        if (chosen.contains(people.get(randomChose))) {
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

            int range = 90 - (90 / people.size() * chosen.indexOf(people.get(randomChose))) + 1;
            int n = random.nextInt(range);
            if (n == 1) {
                OutputManager.showErrMessage(people.get(randomChose).toString());
                chosen.removeIf(p -> p.equals(people.get(randomChose)));
                chosen.addFirst(people.get(randomChose));
                PersonUtility.dataSource.saveChosen(chosen);

            } else {
                choseOne(people,chosen);
            }
        } else {
            System.out.println(people.get(randomChose));
            //we add the chosen
            chosen.addFirst(people.get(randomChose));

            //if all people have been chosen once we remove the last one
            if (chosen.size() >= people.size()) {
                chosen.removeLast();
            }

            PersonUtility.dataSource.saveChosen(chosen);
        }

    }

}
