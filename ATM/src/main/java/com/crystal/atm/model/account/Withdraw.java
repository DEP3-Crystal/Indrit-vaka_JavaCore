package com.crystal.atm.model.account;

public class Withdraw extends Transaction {


    public Withdraw(String description, String reference, double cent) {
        super(description, reference, "Withdraw", cent);
    }

    @Override
    public String getDescription() {
        return null;
    }


    @Override
    public double getCent() {
        return 0;
    }



    @Override
    public String getReference() {
        return null;
    }


    @Override
    public String getType() {
        return null;
    }

    @Override
    public String toString() {
        return "Withdraw{" +
                "dateTimeUTC=" + dateTimeUTC +
                ", description='" + description + '\'' +
                ", reference='" + reference + '\'' +
                ", type='" + type + '\'' +
                ", cent=" + cent +
                '}';
    }
}
