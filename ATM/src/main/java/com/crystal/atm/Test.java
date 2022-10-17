
package com.crystal.atm;

import com.crystal.atm.dao.DataAccess;
import com.crystal.atm.dao.DataFromMemory;
import com.crystal.atm.model.account.Transaction;
import com.crystal.atm.model.user.User;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Test {
    public Test() throws IOException {
        File file = new File("");
        PrintWriter pr = new PrintWriter("");
        pr.close();
//        Files.write()

    }

    public static void main(String[] args) {
        DataAccess dataAccess = new DataFromMemory();

        //PersonService.createPersonFromCLI(dataAccess);

        Map<Integer, User> people = dataAccess.getUsers();

        PrintStream var10001 = System.out;
        Objects.requireNonNull(var10001);
        people.values().forEach(var10001::println);
        User indrit = people.entrySet().stream()
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
