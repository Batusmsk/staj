package com.example.batuhan.hastane;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class register {

    // int patientID;
    // String patientBirthDate;
    // String patientYas;
    int registerNo;
    String patientComplaint;
    String patientRegDate;

    public register(int registerNo, String complaint, String regDate) {

        // this.patientID = identity;
        // this.patientBirthDate = birthDate;
        // this.patientYas = yas;

        this.registerNo = registerNo;
        this.patientComplaint = complaint;
        this.patientRegDate = regDate;
    }

    public int patientRegNoGet() {
        return registerNo;
    }
    /*
     * public int patientIDGet() {
     * return patientID;
     * }
     * 
     * public String patientBirthDateGet() {
     * return this.patientBirthDate;
     * }
     */

    public String patientRegDateGet() {
        return patientRegDate;
    }

    public boolean patientComplaintSet(String complaint) {
        this.patientComplaint = complaint;
        System.out.println("Hasta şikayeti değiştirildi");
        return true;
    }

    public String patientComplaintGet() {
        return this.patientComplaint;
    }

    public String toString() {
        return "patient [kayitNo=" + registerNo + ", sikayet=" + patientComplaint + ", kayitTarih=" + patientRegDate
                + "]";

    }

}
