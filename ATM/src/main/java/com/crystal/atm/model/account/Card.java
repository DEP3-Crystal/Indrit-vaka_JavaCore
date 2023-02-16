//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.crystal.atm.model.account;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Card {
    private final String cardNumber;
    /**
     * The account id
     */
    private final String IBAN;
    private final String BIN;
    private final String CVV;
    private final LocalDate expirationDate;
    private final CardType type;
    private String pin;

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Card(String cardNumber, String IBAN, String BIN, String CVV, LocalDate expirationDate, CardType type, String pin) {
        this.cardNumber = cardNumber;
        this.IBAN = IBAN;
        this.BIN = BIN;
        this.CVV = CVV;
        this.expirationDate = expirationDate;
        this.type = type;
        this.pin = pin;
    }
}
