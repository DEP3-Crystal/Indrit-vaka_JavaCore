package com.crystal.atm.model.account;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
@Data
public abstract class Transaction {
    protected final LocalDateTime dateTimeUTC;
    protected final String description;
    /**
     * Company name where this purchase has been made.
     */
    protected final String reference;
    protected final String type;
    protected final double cent;


    public Transaction(String description, String reference, String type, double cent) {
        dateTimeUTC = LocalDateTime.now(ZoneOffset.UTC);

        this.description = description;
        this.reference = reference;
        this.type = type;
        this.cent = cent;
    }

    public LocalDateTime getDateTimeUTC() {
        return dateTimeUTC;
    }

    public abstract String getDescription();

    public abstract double getCent();

    /**
     * @return Company name where this purchase has been made.
     */
    public abstract String getReference();

    public abstract String getType();
}
