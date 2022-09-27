//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.crystal.atm.model.account;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Card {
    private final CardType type;
    private final int personId;
    private final String cardNumber;
    private final String BIN;
    private final String CVV;
    private final LocalDate expirationDate;
    private String pin;

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Card(CardType type, int personId, String cardNumber, String bin, String cvv, LocalDate expirationDate, String pin) {
        this.type = type;
        this.personId = personId;
        this.cardNumber = cardNumber;
        this.BIN = bin;
        this.CVV = cvv;
        this.expirationDate = expirationDate;
        this.pin = pin;
    }
}
