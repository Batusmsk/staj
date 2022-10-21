package com.example.batuhan.hospital;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Patient {

    private int patientID;
    private String patientFullName;
    private String patientBirthDate;

    private List<Register> patients;

    public Patient(int ID, String fullName, String birthDate) {

        this.patientID = ID;
        this.patientFullName = fullName;
        this.patientBirthDate = birthDate;

        patients = new ArrayList<>();
    }

    public int getPatientID() {
        return patientID;
    }

    public String getPatientFullName() {
        return patientFullName;
    }

    public String setPatientFullName(String fullName) {
        return this.patientFullName = fullName;
    }

    public String getPatientBirthDate() {
        return patientBirthDate;
    }

    public int getNumberOfRegister() {
        return patients.size();
    }

    public void addPatient(Register pat) {
        this.patients.add(pat);
    }

    public Register getPatientReg(int index) {
        return this.patients.get(index);
    }

    public Optional<Register> getPatientRegisters(int regNo) {

        for (Register pat : patients) {
            if (pat.patientRegNoGet() == regNo) {
                return Optional.of(pat);
            }
        }
        return Optional.empty();
    }

    public Optional<Register> removePatientReg(String regNo) {

        Optional<Register> Register = getPatientRegisters(Integer.parseInt(regNo));
        Register.ifPresentOrElse(value -> patients.remove(Register.get()),
                () -> System.out.println("Böyle bir kayıt bulunamadı."));
        return Register;
    }

    public Register removePatientReg(int index) {
        return this.patients.remove(index);
    }

    public ArrayList<Integer> toArray() {
        ArrayList<Integer> array = new ArrayList<>();
        for (Register reg : patients) {
            array.add(reg.patientRegNoGet());
        }
        return array;
    }

}
