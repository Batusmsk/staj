package com.example.batuhan.app;

import com.example.batuhan.proje.Account;

public class AccountApp {
            
	public static void main(String[] args) {

		Account acc;
		acc = new Account("TR1", 50000);
		
		acc.withdraw(10000);
		acc.deposit(32500);
		System.out.println(acc.toString());
	}

}
