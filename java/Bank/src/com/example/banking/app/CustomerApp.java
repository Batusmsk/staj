package com.example.banking.app;

import java.util.function.Consumer;

import com.example.banking.proje.Account;
import com.example.banking.proje.Customer;
import com.example.banking.proje.InsufficientBalanceException;

public class CustomerApp {
    public static void main(String[] args) {

        Customer jack = new Customer("1", "Jack Bauer");

        Account acc1 = new Account("TR1", 15000);
        Account acc2 = new Account("TR2", 10000);
        Account acc3 = new Account("TR3", 10000);

        jack.addAccount(acc1);
        jack.addAccount(acc2);
        jack.addAccount(acc3);

        System.out.println("Hesap sayısı: " + jack.getNumberOfAccount());

        Consumer<Account> withdraw45k = acc -> {
            try {
                acc.withdraw(5000);
            } catch (InsufficientBalanceException e) {
                e.printStackTrace();
            }
        };
        Consumer<Account> withdraw5k = acc -> {
            try {
                acc.withdraw(0);
            } catch (InsufficientBalanceException e) {
                e.printStackTrace();
            }
        };

        jack.getAccount("TR1").ifPresentOrElse(
                withdraw45k.andThen(withdraw5k), () -> System.out.println("Böyle bir hesap bulunamadı"));

        jack.removeAccount("TR2").ifPresentOrElse(acc -> System.out.println(acc.getIban() + " Ibanlı hesap silindi."), () -> System.out.println("Silmek istediğiniz hesap bulunamadı"));
        System.out.println("Hesap sayısı: " + jack.getNumberOfAccount());

        for (String account : jack.toArray()) {
            System.out.println("Hesap ismi: " + account + " Bakiye: " + jack.getAccount(account).get().getBalance());
            
        }
        System.out.println("Hesaplarınızın toplam bakiyesi: " + jack.getBalance());
        

    }
    
}
