package com.example.batuhan.proje;

public class Account {

	private final String iban;
	protected double balance;
	
	// alt + shift + s constructor fields
	public Account(String iban, double string) {
		this.iban = iban;
		this.balance = string;

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
		System.out.println("Hesabınıza " +amount+ " tutarında para yatırıldı. Yeni bakiyeniz " + this.balance);
		return true;
	}

	public boolean withdraw(double amount) {
		if (amount <= 0.)
			return false;
		if (amount > balance) {
			System.out.println(amount + " Tutarı hesabınızdaki para tutarını aşıyor");
			return false;
		}
		this.balance = this.balance - amount;
		System.out.println("Hesabınızdan " +amount+ " tutarında para çekildi. Yeni bakiyeniz " + this.balance);
		return true;
	}

	@Override
	public String toString() {
		return "Account [iban=" + iban + ", balance=" + balance + "]";
	}

}
