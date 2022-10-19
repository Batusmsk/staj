package com.example.batuhan.uygulama;

import com.example.batuhan.hastane.kayit;

public class hastaUygulama {
    public static void main(String[] args) {

        kayit hasta;

        hasta = new kayit(123456, "08.09.2004", "Öksürük", "19.10.2022");
        
        System.out.println(hasta.hastaSikayetGet());
        System.out.println(hasta.toString());
    }
}
