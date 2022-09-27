//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.crystal.atm.model.account;

public class Withdraw extends Transaction {
    public Withdraw(String description, String reference, long cent) {
        super(description, reference, "Withdraw", cent);
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
        return "Withdraw{dateTimeUTC=" + this.dateTimeUTC + ", description='" + this.description + "', reference='" + this.reference + "', type='" + this.type + "', cent=" + this.amount + "}";
    }
}
