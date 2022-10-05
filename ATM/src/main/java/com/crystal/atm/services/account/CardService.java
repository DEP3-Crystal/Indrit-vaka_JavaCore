//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.crystal.atm.services.account;

import com.crystal.atm.model.account.Card;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class CardService {

    public Optional<Card> getCard(Map<String, Card> cards, String cardNumber) {
        return cards.entrySet().stream()
                .dropWhile(card -> !card.getKey().equals(cardNumber))
                .takeWhile(card -> card.getKey().equals(cardNumber))
                .map(Map.Entry::getValue)
                .findFirst();
    }

    public String generatePin() {
        ThreadLocalRandom var10000 = ThreadLocalRandom.current();
        String pin = "" + var10000.nextInt(1, 10);
        pin = pin + ThreadLocalRandom.current().nextInt(1, 10);
        pin = pin + ThreadLocalRandom.current().nextInt(1, 10);
        pin = pin + ThreadLocalRandom.current().nextInt(1, 10);
        return pin;
    }

    public void changePin(Card card, String pin) {
        card.setPin(pin);
    }
}
