package com.crystal.atm;

import com.crystal.atm.dao.DataAccess;
import com.crystal.atm.dao.DataFromMemory;
import com.crystal.atm.model.person.Person;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DataAccess dataAccess = new DataFromMemory();
        List<Person> people = dataAccess.getPeople();

        people.forEach(System.out::println);
        Person indrit = people.stream().takeWhile(person -> person.getFirstName().equalsIgnoreCase("indrit")).findFirst().orElseThrow();

        //let's make a deposit on first account of indrit
        indrit.getAccounts().get(0).deposit("Monthly paycheck", "crystal-system", 500_00);
        //let's make a withdrawal on first account
        indrit.getAccounts().get(0).withdraw("mobile recharge", "vodafone albania", 15_00);
        //let's se the balance we expect to be 48500
        System.out.println(indrit.getAccounts().get(0).getBalance());
        //lest se all the transactions
        System.out.println("Indrit vaka transactions");
        indrit.getAccounts().get(0).getTransactions().forEach(System.out::println);
    }
}
