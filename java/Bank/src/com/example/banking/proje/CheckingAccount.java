package com.example.banking.proje;

public class CheckingAccount extends Account {
    private double overDraftAmount;

    public CheckingAccount(String iban, double balance, double overDraftAmount) {
        super(iban, balance);
        this.overDraftAmount = overDraftAmount;
    }

    public double getOverdraftAmount() {
        return overDraftAmount;
    }
    
    @Override
    public void withdraw(double amount) throws InsufficientBalanceException {
        System.out.println("CheckingAccount::withdraw");
        if(amount <= 0.)
            throw new IllegalArgumentException("Miktar negatif olamaz");
         if(amount > (balance+overDraftAmount))
             throw new InsufficientBalanceException("Yetersiz bakiye", this.balance-amount-overDraftAmount);
        this.balance = this.balance - amount;

    }
}
