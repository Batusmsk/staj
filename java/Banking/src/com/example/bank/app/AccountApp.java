package com.example.bank.app;

import java.util.Scanner;

import com.example.bank.proje.Account;

public class AccountApp {

	public static void main(String[] args) {

		boolean x = true;
		Account acc;
		acc = new Account("TR1", 50000);

		Scanner input = new Scanner(System.in);

		String islem;
		int miktar;

		if (x != true) {

			System.out.print("Yapmak istediğiniz işlemi girin. Yatir, Cek veya Bakiye: ");
			islem = input.nextLine();

			if (islem.toLowerCase().equals("yatir")) {

				System.out.println("Yatırmak istediğiniz miktarı giriniz: ");
				miktar = input.nextInt();
				acc.deposit(miktar);

			} else if (islem.toLowerCase().equals("cek")) {
				System.out.print("Çekmek istediğiniz miktarı giriniz: ");
				miktar = input.nextInt();
				acc.withdraw(miktar);

			} else if (islem.toLowerCase().equals("bakiye")) {
				System.out.println("Toplam bakiyeniz: " + acc.getBalance());

			} else {
				System.out.println("Böyle bir işlem bulunamadı");
			}

		} else {
			acc.withdraw(10000);
			acc.deposit(32500);
		}

		System.out.println(acc.toString());
	}

}
