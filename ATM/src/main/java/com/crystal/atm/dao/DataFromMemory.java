package com.crystal.atm.dao;

import com.crystal.atm.model.account.Account;
import com.crystal.atm.model.person.Address;
import com.crystal.atm.model.person.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataFromMemory implements DataAccess {
    List<Person> people = new ArrayList<>();

    {
        //Address details *
        //TODO Validation
        Address address = new Address("street...", "Tirane", "Albania", "Albania", "1001");
        //Creating some persons
        Account account = new Account("ACV225478AA");
        Person indrit =
                new Person(0, "Indrit",
                        "Vaka",
                        LocalDate.of(2001, 1, 1),
                        "0676926458",
                        "indrit.vaka@crystal-system.eu",
                        address);
        indrit.addAccount(account);

        Person luka =
                new Person(1, "Luka",
                        "Buziu",
                        LocalDate.of(2001, 1, 1),
                        "055461895",
                        "luka.buziu@crystal-system.eu",
                        address);
        luka.addAccount(account);

        Person dmitri =
                new Person(2, "Dmitri",
                        "Kittredge",
                        LocalDate.of(1997, 10, 11),
                        "654-718-4693",
                        "dkittredge0@flickr.com",
                        address);
        dmitri.addAccount(account);
        people.add(indrit);
        people.add(luka);
        people.add(dmitri);
    }

    @Override
    public List<Person> getPeople() {
        return people;
    }

    @Override
    public Person getPerson(int id) {
        return people.stream().takeWhile(person -> person.getId() == id).findFirst().orElseThrow();
    }

    @Override
    public void savePerson(Person person) {
        people.add(person);
    }

    @Override
    public void savePeople(List<Person> people) {
        this.people.addAll(people);
    }
}
