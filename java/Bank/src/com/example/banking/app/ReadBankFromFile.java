package com.example.banking.app;

import com.example.banking.proje.Bank;

import java.io.*;

public class ReadBankFromFile {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File file = new File("./", "mybank.dat");
        try(
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
        ){
            Bank bank = (Bank) ois.readObject();
            bank.getCustomer("1").ifPresentOrElse(System.out::println, () -> System.out.println("Müşteri bulunamadı"));
            bank.getCustomer("2").ifPresentOrElse(System.out::println, () -> System.out.println("Müşteri bulunamadı"));

        }

    }
}
