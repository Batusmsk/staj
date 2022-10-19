package com.example.batuhan.hastane;

public class kayit {

    int hastaTC;
    String hastaDogumTarih;
    //String hastaYas;
    String hastaSikayet;
    String hastaKayitTarih;

    public kayit(int tc, String dogumTarih,String sikayet, String kayitTarih) {

        this.hastaTC = tc;
        this.hastaDogumTarih = dogumTarih;
        //this.hastaYas = yas;
        this.hastaSikayet = sikayet;
        this.hastaKayitTarih = kayitTarih;
    }

    public int hastaTcGet() {
        return hastaTC;
    }

    public String hastaDogumTarihiGet() {
        return this.hastaDogumTarih;
    }

    public String hastaKayitTarih() {
        return hastaKayitTarih();
    }

    public boolean hastaKayitTarihDegistir(String tarih) {
        this.hastaKayitTarih = tarih;
        return true;
    }

    public boolean hastaSikayetSet(String sikayet) {
        this.hastaSikayet = sikayet;
        System.out.println("Hasta şikayeti değiştirildi");
        return true;
    }

    public String hastaSikayetGet() {
        return this.hastaSikayet;
    }

    public String toString() {
        return "Hasta [kimlik=" +hastaTC+", dogumTarih="+hastaDogumTarih+", sikayet="+hastaSikayet+", kayitTarih=" +hastaKayitTarih+"]";
        
    }

}
