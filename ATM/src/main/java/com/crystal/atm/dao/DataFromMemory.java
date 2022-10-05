package com.crystal.atm.dao;

import com.crystal.atm.model.CurrencyType;
import com.crystal.atm.model.account.Account;
import com.crystal.atm.model.account.Card;
import com.crystal.atm.model.account.CardType;
import com.crystal.atm.model.user.Address;
import com.crystal.atm.model.user.User;
import com.crystal.atm.services.account.CardService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataFromMemory implements DataAccess {
    Map<Integer, User> users;
    Map<String, Card> cards;
    Map<String, Account> accounts;
    String iban1 = "iban1";
    String iban2 = "iban2";

    //Loading Cards
    {
        List<Card> cards = new ArrayList<>();
        var cardService = new CardService();
        cards.add(new Card(
                "000000000000",
                iban1,
                "1234",
                "225",
                LocalDate.now().plusYears(3),
                CardType.CREDIT_CARD,
                "123"
        ));
        cards.add(new Card(
                "111111111111",
                iban2,
                "1234",
                "125",
                LocalDate.now().plusYears(3),
                CardType.DEBIT_CARD,
                cardService.generatePin()));
        cards.add(new Card(
                "222222222222",
                iban1,
                "1234",
                "125",
                LocalDate.now().plusYears(3),
                CardType.CREDIT_CARD,
                cardService.generatePin()));

        this.cards = cards.stream()
                .collect(Collectors.toMap(Card::getCardNumber, card -> card));
    }

    //Loading accounts
    {
        List<Account> accounts = new ArrayList<>();
        var account = new Account(iban1, 1, CurrencyType.EURO);

        account.addCard(cards.values().stream()
                .filter(card -> card.getIBAN().equals(iban1))
                .findFirst()
                .orElseThrow()
        );
        var account2 = new Account(iban2, 2, CurrencyType.EURO);

        account2.addCard(cards.values().stream()
                .filter(card -> card.getIBAN().equals(iban2))
                .findFirst()
                .orElseThrow());

        accounts.add(account);
        accounts.add(account2);
        this.accounts = accounts.stream().collect(Collectors.toMap(Account::getIBAN, a -> a));
    }

    //Loading People
    {
        //Address details *
        //TODO Validation
        Address address = new Address(1, "street...", "Tirane", "Albania", "Albania", "1001");
        //Creating some persons
        Account account = new Account("ACV225478AA", 2, CurrencyType.EURO);
        User indrit =
                new User(1, "Indrit",
                        "Vaka",
                        LocalDate.of(2001, 1, 1),
                        "0676926458",
                        "indrit.vaka@crystal-system.eu",
                        address);
        //indrit.addAccount(account);
        User luka =
                new User(2, "Luka",
                        "Buziu",
                        LocalDate.of(2001, 1, 1),
                        "055461895",
                        "luka.buziu@crystal-system.eu",
                        address);
        //luka.addAccount(account);

        User dmitri =
                new User(3, "Dmitri",
                        "Kittredge",
                        LocalDate.of(1997, 10, 11),
                        "654-718-4693",
                        "dkittredge0@flickr.com",
                        address);
        //dmitri.addAccount(account);
        List<User> people = new ArrayList<>();
        people.add(indrit);
        people.add(luka);
        people.add(dmitri);


        people = people.stream()
                .parallel()
                .peek(user -> {
                    // TODO Question
                    user.setAccounts(this.accounts.values().stream()
                            .filter(v -> v.getUserId() == user.getUserId())
                            .collect(Collectors.toList())
                    );
                }).collect(Collectors.toList());

        this.users = people.stream()
                .collect(Collectors.toMap(User::getUserId, person -> person));


    }

    @Override
    public Map<Integer, User> getUsers() {
        return users;
    }

    @Override
    public User getUser(int id) {
        return users.entrySet().stream().takeWhile(person -> person.getKey() == id)
                .map(Map.Entry::getValue)
                .findFirst().orElseThrow();
    }

    @Override
    public void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    @Override
    public void addUsers(Map<Integer, User> people) {
        this.users.putAll(people);
    }

    @Override
    public Map<String, Card> getCards() {
        return cards;
    }

    @Override
    public Map<String, Account> getAccounts() {
        return accounts;
    }

    @Override
    public void addAccount(Account account) {
        accounts.put(account.getIBAN(), account);
    }
}
