package com.crystal.atm.model.account;

import lombok.Data;

import java.time.LocalDate;
@Data
public class Card {
    private final CardType type;
    private final String CardNumber;

    /**
     * Bank Identification Number
     */
    private final String BIN;

    /**
     * Card Verification Value
     */
    private final String CVV;
    private final LocalDate expirationDate;
    private String pin;

    public void setPin(String pin) {
        //TODO Some validation
        this.pin = pin;
    }

    public Card(CardType type, String cardNumber, String bin, String cvv, LocalDate expirationDate) {
        this.type = type;
        CardNumber = cardNumber;
        BIN = bin;
        CVV = cvv;
        this.expirationDate = expirationDate;
    }

}
