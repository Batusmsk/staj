package com.example.batuhan.app;
import java.util.function.Consumer;

import com.example.batuhan.proje.Account;
import com.example.batuhan.proje.Customer;

public class customerApp {
    public static void main(String[] args){
      
        Customer jack = new Customer("1", "Jack Bauer");

        Account acc1 = new Account("TR1", 10000);
        Account acc2 = new Account("TR1", 10000);
        Account acc3 = new Account("TR1", 10000);

        jack.addAccount(acc1);
        jack.addAccount(acc2);
        jack.addAccount(acc3);

        System.out.println("Hesap sayısı: " + jack.getNumberOfAccount());
        Consumer<Account> withdraw45k = acc -> acc.withdraw(45000);
        Consumer<Account> withdraw5k = acc -> acc.withdraw(5000);

        jack.getAccount("TR1").ifPresent(
            withdraw45k.andThen(withdraw5k));

    }
}
