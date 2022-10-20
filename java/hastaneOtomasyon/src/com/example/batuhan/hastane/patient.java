package com.example.batuhan.hastane;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class patient {

    private int patientID;
    private String patientFullName;
    private String patientBirthDate;

    private List<register> patients;

    public patient(int ID, String fullName, String birthDate) {

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

    public void addPatient(register pat) {
        this.patients.add(pat);
    }

    public register getPatientReg(int index) {
        return this.patients.get(index);
    }

    public Optional<register> getPateintRegisters(int regNo) {

        for (register pat : patients) {
            if (pat.patientRegNoGet() == regNo) {
                return Optional.of(pat);
            }
        }
        return Optional.empty();
    }

    public Optional<register> removePatientReg(String regNo) {

        Optional<register> register = getPateintRegisters(Integer.parseInt(regNo));
        register.ifPresentOrElse(value -> patients.remove(register.get()),
                () -> System.out.println("Böyle bir kayıt bulunamadı."));
        return register;
    }

    public register removePatientReg(int index) {
        return this.patients.remove(index);
    }

    public ArrayList<Integer> toArray() {
        ArrayList<Integer> array = new ArrayList<>();
        for (register reg : patients) {
            array.add(reg.patientRegNoGet());
        }
        return array;
    }

}
