package com.example.batuhan.app;

import java.util.ArrayList;
import java.util.Scanner;

import com.example.batuhan.hospital.*;

public class PatientApp {

    public static void main(String[] Args) {

        Patient batuhan = new Patient(1234, "09.09.2004", "Batuhan Şimşek");

        Register pat1 = new Register(1, "Öksürük", "20.10.2022");
        Register pat2 = new Register(2, "boğaz ağrısı", "20.10.2021");

        batuhan.addPatient(pat1);
        batuhan.addPatient(pat2);

        System.out.println("Kayıt Bilgisi: " + batuhan.getPatientRegisters(1));
        System.out.println("Kayıt Bilgisi: " + batuhan.getPatientRegisters(2));
        System.out.println("Kayıt Numaraları: " + batuhan.toArray());
        batuhan.removePatientReg("1");
        System.out.println("Kayıt Numaraları: " + batuhan.toArray());
        System.out.println("Toplam Kayıt: " + batuhan.getNumberOfRegister());

    }

}
