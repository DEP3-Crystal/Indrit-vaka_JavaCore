package com.crystal.atm.model.account;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Data
public class Transaction {
    // TODO
   // private final String accountId;
    private final LocalDateTime dateTimeUTC;
    private final String description;
    /**
     * Company name where this purchase has been made.
     */
    private final String reference;
    private final String type;
    private final long amount;


    public Transaction(String description, String reference, String type, long amount) {
        this.dateTimeUTC = LocalDateTime.now(ZoneOffset.UTC);

        this.description = description;
        this.reference = reference;
        this.type = type;
        this.amount = amount;
    }
}
