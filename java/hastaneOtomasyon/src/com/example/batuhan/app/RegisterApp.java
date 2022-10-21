package com.example.batuhan.app;

import com.example.batuhan.hospital.Register;

public class RegisterApp {
    public static void main(String[] args) {

        Register patient;

        patient = new Register(1, "Öksürük", "19.10.2022");

        System.out.println(patient.patientComplaintGet());
        System.out.println(patient.toString());
        patient.patientComplaintSet("Boğaz ağrısı");
        System.out.println(patient.patientComplaintGet());

    }
}
