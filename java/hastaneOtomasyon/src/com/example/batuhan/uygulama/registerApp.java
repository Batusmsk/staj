package com.example.batuhan.uygulama;

import com.example.batuhan.hastane.register;

public class registerApp {
    public static void main(String[] args) {

        register patient;

        patient = new register(1, "Öksürük", "19.10.2022");

        System.out.println(patient.patientComplaintGet());
        System.out.println(patient.toString());
        patient.patientComplaintSet("Boğaz ağrısı");
        System.out.println(patient.patientComplaintGet());

    }
}
