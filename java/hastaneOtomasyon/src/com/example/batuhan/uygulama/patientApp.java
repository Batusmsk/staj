package com.example.batuhan.uygulama;

import com.example.batuhan.hastane.patient;
import com.example.batuhan.hastane.register;

public class patientApp {

    public static void main(String[] Args) {

        patient batuhan = new patient(1234, "09.09.2004", "Batuhan Şimşek");

        register pat1 = new register(1, "Öksürük", "20.10.2022");
        register pat2 = new register(2, "boğaz ağrısı", "20.10.2021");

        batuhan.addPatient(pat1);
        batuhan.addPatient(pat2);

        System.out.println("Kayıt Bilgisi: " + batuhan.getPateintRegisters(1));
        System.out.println("Kayıt Bilgisi: " + batuhan.getPateintRegisters(2));
        System.out.println("Kayıt Numaraları: " + batuhan.toArray());
        batuhan.removePatientReg("1");
        System.out.println("Kayıt Numaraları: " + batuhan.toArray());
        System.out.println("Toplam Kayıt: " + batuhan.getNumberOfRegister());

    }

}
