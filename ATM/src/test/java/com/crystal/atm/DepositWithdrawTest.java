package com.crystal.atm;

import com.crystal.atm.dao.DataAccess;
import com.crystal.atm.dao.DataFromMemory;
import com.crystal.atm.model.account.Account;
import com.crystal.atm.model.person.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class DepositWithdrawTest {
    DataAccess dataAccess = new DataFromMemory();
    Person person;

    @BeforeEach
    void loadData() {
        Map<Integer, Person> people = dataAccess.getPeople();
        //let's take first person on the list
        person = people.get(0);
    }

    @Test
    public void checkDeposit() {

        //let's use first account
        Account firstAccount = person.getAccounts().get(0);
        long balance = firstAccount.getBalance();
        //Depositing
        long amount = 500_00;
        firstAccount.deposit("monthly payment", "crystal-system", amount);
        Assertions.assertEquals(balance, person.getAccounts().get(0).getBalance() - amount);
    }

    @Test
    public void checkWithdraw() {
        Account account = person.getAccounts().get(0);
        long balance = account.getBalance();
        long amount = 20_00;
        account.withdraw("mobile recharge","vodafone albania",amount);
        Assertions.assertEquals(balance, person.getAccounts().get(0).getBalance()+amount);
    }
}
