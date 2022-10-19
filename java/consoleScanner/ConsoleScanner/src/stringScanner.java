import java.util.Scanner;

public class stringScanner {
 
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String isim,sifre1,sifre2;

        System.out.print("Lütfen isminizi girin: ");
        isim = input.nextLine();

        System.out.print("Lütfen şifreninizi girin: ");
        sifre1 = input.next();
        System.out.print("Lütfen şifrenizi tekrar yazınız: ");
        sifre2 = input.next();



        while(!sifre1.equals(sifre2)) {
            System.out.println("Şifreniz birbirleriyle uyuşmuyor lütfen tekrar deneyin");

            System.out.print("Lütfen şifreninizi girin: ");
            sifre1 = input.next();
            System.out.print("Lütfen şifrenizi tekrar yazınız: ");
            sifre2 = input.next();

        }
        
        System.out.println("İsminiz: " + isim);
        System.out.println("Şifreniz: " + sifre1);















        /* 
        while (sifre1!=sifre2) {
            System.out.println("Şifreler birbirleriyle uyuşmuyor lütfen tekrar deneyin");
            
            System.out.print("Lütfen şifreninizi girin: ");
            sifre1 = input.next();
            System.out.print("Lütfen şifrenizi tekrar yazınız: ");
            sifre2 = input.next();
        } 
        */


     }

}
