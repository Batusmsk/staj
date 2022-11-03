package com.example.banking.app;

import com.example.banking.proje.Account;
import com.example.banking.proje.Bank;
import com.example.banking.proje.CheckingAccount;

import java.io.*;

public class WriteBankToFile {
    public static void main(String[] args) throws IOException {
        Bank bank = new Bank(1, "mybank");

        var batu = bank.createCustomer("1", "Batuhan Şimşek");
        var hayri = bank.createCustomer("2", "Hayri Şimşek");

        batu.addAccount(new Account("TR1", 100000));
        batu.addAccount(new CheckingAccount("TR2", 200000, 5000));

        hayri.addAccount(new Account("TR3", 150000));
        hayri.addAccount(new CheckingAccount("TR4", 10000, 2000));

        File file = new File("./", "mybank.dat");
        FileOutputStream fos = new FileOutputStream(file);

        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(bank);
        oos.close();
        fos.close();

    }
}
