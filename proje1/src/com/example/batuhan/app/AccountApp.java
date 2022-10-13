package com.example.batuhan.app;

import com.example.batuhan.proje.Account;
//ctrl + shift + o

public class AccountApp {

	public static void main(String[] args) {

		Account acc;
		acc = new Account("TR1", 100000);
		
		System.out.println("Balance: " + acc.getBalance());
		
		acc.withdraw(150000);

		System.out.println("Balance: " + acc.getBalance());
		acc.deposit(32500);
		System.out.println("Balance: " + acc.getBalance());
		System.out.println(acc.toString());
	}

}
