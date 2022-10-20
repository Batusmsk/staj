package com.example.bank.proje;

public class checkingAccount extends Account {
    private double overDraftAmount;

    public checkingAccount(String iban, double balance, double overDraftAmount) {
        super(iban, balance);
        this.overDraftAmount = overDraftAmount;
    }

    public double getOverdraftAmount() {
        return overDraftAmount;
    }
    
    @Override
    public boolean withdraw(double amount) {
        System.out.println("CheckingAccount::withdraw");
        if(amount <= 0.)
            return false;
         if(amount > (balance+overDraftAmount))
            return false;
        this.balance = this.balance - amount;
        return true;
    }
}
