package com.crystal.atm.model.account;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Data
public class Transaction {

    protected final LocalDateTime dateTimeUTC;
    protected final String description;
    /**
     * Company name where this purchase has been made.
     */
    protected final String reference;
    protected final String type;
    protected final long amount;


    public Transaction(String description, String reference, String type, long amount) {
        this.dateTimeUTC = LocalDateTime.now(ZoneOffset.UTC);

        this.description = description;
        this.reference = reference;
        this.type = type;
        this.amount = amount;
    }
}
