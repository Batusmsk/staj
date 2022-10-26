package com.example.banking.proje;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Customer {

    private final String identityNo;
    private String fullName;
    private List<Account> accounts;

    public Customer(String identityNo, String fullName) {

        this.identityNo = identityNo;
        this.fullName = fullName;

        accounts = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(identityNo, customer.identityNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identityNo);
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String name) {
        this.fullName = name;
    }

    public int getNumberOfAccount() {

        return accounts.size();
    }

    public Account getAccount(int index) {
        return accounts.get(index);
    }

    public Optional<Account> getAccount(String iban) {

        for (Account acc : accounts) {
            if (acc.getIban().equals(iban)) {
                return Optional.of(acc);
            }

        }
        // System.out.println(iban + "İbanlı bir hesap bulunamadı.");
        return Optional.empty();
    }

    public ArrayList<String> toArray() {

        ArrayList<String> array = new ArrayList<String>(); 
    
        for (Account acc : accounts) {
            array.add(acc.getIban());
            //System.out.println(acc.getIban());
        }
        return array;
    }

    public void addAccount(Account acc) {

        this.accounts.add(acc);
    }

    public Account removeAccount(int index) {

        return accounts.remove(index);
    }

    public Optional<Account> removeAccount(String iban) {

        Optional<Account> account = getAccount(iban);
        account.ifPresent(value -> accounts.remove(account.get()));
        return account;
    }

    public double getBalance() {
        double totalBalance = 0.;

        for (Account acc : accounts) {
            totalBalance += acc.getBalance();
        }
         return totalBalance;
    }
}
