package com.example.banking.proje;

import java.util.Objects;

public class Account {
    public static int counter = 1;
    private AccountStatus status = AccountStatus.ACTIVE;
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


    public boolean deposit(double amount) {
        if (amount <= 0.)
            return false;
        this.balance = this.balance + amount;
        System.out.println("Hesabınıza " + amount + " tutarında para yatırıldı. Yeni bakiyeniz " + this.balance);
        return true;
    }

    public boolean withdraw(double amount) {
        System.out.println("Account::withdraw");
        if (amount <= 0.)
            return false;
        if (amount > balance) {
            System.out.println(amount + " Tutarı hesabınızdaki para tutarını aşıyor");
            return false;
        }
        this.balance = this.balance - amount;
        System.out.println("Hesabınızdan " + amount + " tutarında para çekildi. Yeni bakiyeniz " + this.balance);
        return true;
    }

    @Override
    public String toString() {
        return "Account [iban=" + iban + ", balance=" + balance + "]";
    }

}
