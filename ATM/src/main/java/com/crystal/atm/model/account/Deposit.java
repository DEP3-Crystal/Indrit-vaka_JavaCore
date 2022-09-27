//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.crystal.atm.model.account;

public class Deposit extends Transaction {
    public Deposit(String description, String reference, long amount) {
        super(description, reference, "Deposit", amount);
    }

    public String getDescription() {
        return null;
    }

    public long getAmount() {
        return 0L;
    }

    public String getReference() {
        return null;
    }

    public String getType() {
        return null;
    }

    public String toString() {
        return "Deposit{dateTimeUTC=" + this.dateTimeUTC + ", description='" + this.description + "', reference='" + this.reference + "', type='" + this.type + "', cent=" + this.amount + "}";
    }
}
