package com.example.banking.proje;

import javax.naming.InsufficientResourcesException;
import java.io.Serializable;
import java.util.Objects;

public class Account implements Serializable {
    public static int counter = 1;
    private transient AccountStatus status = AccountStatus.ACTIVE;
    private final String iban;
    protected double balance;

    // alt + shift + s constructor fields
    public Account(String iban, double string) {
        this.iban = iban;
        this.balance = string;

		counter++;
    }

    public AccountStatus getStatus(AccountStatus status) {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(iban, account.iban);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iban);
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public String getIban() {
        return iban;
    }

    public double getBalance() {
        return balance;
    }


    public final void deposit(double amount) {
        if (amount <= 0.)
            throw new IllegalArgumentException("Miktar negatif olamaz");
        this.balance = this.balance + amount;

    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        System.out.println("Account::withdraw");
        if (amount <= 0.)
            throw new IllegalArgumentException("Miktar negatif olamaz");
        if (amount > balance) {
            throw new InsufficientBalanceException("Yetersiz bakiye", this.balance-amount);
        }
        this.balance = this.balance - amount;
    }

    @Override
    public String toString() {
        return "Account [iban=" + iban + ", balance=" + balance + "]";
    }

}
