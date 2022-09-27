package com.crystal.atm.dao;

import com.crystal.atm.model.account.Account;
import com.crystal.atm.model.account.Card;
import com.crystal.atm.model.account.CardType;
import com.crystal.atm.model.person.Address;
import com.crystal.atm.model.person.Person;
import com.crystal.atm.services.account.CardService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataFromMemory implements DataAccess {
    List<Person> people = new ArrayList<>();
    List<Card> cards = new ArrayList<>();

    //Loading Cards
    {
        var cardService = new CardService();
        cards.add(new Card(
                CardType.CREDIT_CARD,
                0,
                "000000000000",
                "1234",
                "225",
                LocalDate.now().plusYears(3),
                cardService.generatePin()));
        cards.add(new Card(
                CardType.CREDIT_CARD,
                1,
                "111111111111",
                "1234",
                "125",
                LocalDate.now().plusYears(3),
                cardService.generatePin()));
        cards.add(new Card(
                CardType.CREDIT_CARD,
                1,
                "222222222222",
                "1234",
                "125",
                LocalDate.now().plusYears(3),
                cardService.generatePin()));
    }

    //Loading People
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
        indrit.getAccounts().get(0).addCard(cards.get(0));
        Person luka =
                new Person(1, "Luka",
                        "Buziu",
                        LocalDate.of(2001, 1, 1),
                        "055461895",
                        "luka.buziu@crystal-system.eu",
                        address);
        luka.addAccount(account);
        luka.getAccounts().get(0).addCard(cards.get(0));

        Person dmitri =
                new Person(2, "Dmitri",
                        "Kittredge",
                        LocalDate.of(1997, 10, 11),
                        "654-718-4693",
                        "dkittredge0@flickr.com",
                        address);
        dmitri.addAccount(account);
        dmitri.getAccounts().get(0).addCard(cards.get(0));

        people.add(indrit);
        people.add(luka);
        people.add(dmitri);
    }

    @Override
    public Map<Integer, Person> getPeople() {
        return people.stream()
                .collect(Collectors.toMap(Person::getId, person -> person));
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
    @Override
    public Map<String, Card> getCards() {
        return cards.stream().collect(Collectors.toMap(Card::getCardNumber, card -> card));
    }
}
