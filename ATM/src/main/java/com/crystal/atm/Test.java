//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.crystal.atm;

import com.crystal.atm.dao.DataAccess;
import com.crystal.atm.dao.DataFromMemory;
import com.crystal.atm.model.account.Transaction;
import com.crystal.atm.model.person.Person;
import com.crystal.atm.services.PersonService;

import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Test {
    public Test() {
    }

    public static void main(String[] args) {
        PersonService.createPersonFromCLI();

        DataAccess dataAccess = new DataFromMemory();
        Map<Integer, Person> people = dataAccess.getPeople();

        PrintStream var10001 = System.out;
        Objects.requireNonNull(var10001);
        people.values().forEach(var10001::println);
        Person indrit = people.entrySet().stream()
                .takeWhile((person) -> person.getValue().getFirstName().equalsIgnoreCase("indrit"))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseThrow();

        indrit.getAccounts().get(0).deposit("Monthly paycheck", "crystal-system", 50000L);
        indrit.getAccounts().get(0).withdraw("mobile recharge", "vodafone albania", 1500L);
        System.out.println(indrit.getAccounts().get(0).getBalance());
        System.out.println("Indrit vaka transactions");
        List<Transaction> transactions = indrit.getAccounts().get(0).getTransactions();
        var10001 = System.out;
        Objects.requireNonNull(var10001);
        transactions.forEach(var10001::println);
    }
}
